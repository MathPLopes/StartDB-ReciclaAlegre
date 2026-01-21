package db.start.reciclaalegre.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import db.start.reciclaalegre.model.Usuario;

@Service
public class JwtService {

    private String secreto = "banana"; //não use assim, defina uma var de ambiente

    public String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secreto);
        return JWT.create()
                //definir o tempo de validade do token
                .withExpiresAt(LocalDateTime.now().
                    plusHours(2L).toInstant(ZoneOffset.of("-03:00")))
                //define quem gerou o token
                .withIssuer("ReciclAlegre")
                //define o dono do token
                .withSubject(usuario.getEmail())
                //claim adicional
                .withClaim("id", usuario.getId())
                //assinatura do token (sign)
                .sign(algorithm);
    }

    public String recuperarSubject(String token) { //Recebe o token do filtro
        Algorithm algorithm = Algorithm.HMAC256(secreto); //monta o mesmo alg de assinatura

        
        return JWT.require(algorithm)
                .withIssuer("ReciclAlegre") //garante a origem
                .build()
                .verify(token) //verifica
                //resultado é o Subject, que no nosso caso é o email
                // mas poderia ser um username, ou algum outro identificador unico
                .getSubject(); 
    }
}
