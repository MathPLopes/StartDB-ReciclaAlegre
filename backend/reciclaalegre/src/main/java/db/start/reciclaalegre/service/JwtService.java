package db.start.reciclaalegre.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import db.start.reciclaalegre.model.Usuario;

@Service
public class JwtService {

    private String secreto = "banana";

    public String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secreto);
        return JWT.create()
                .withExpiresAt(LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00")))
                .withIssuer("ReciclAlegre")
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .sign(algorithm);
    }
}
