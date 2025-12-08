package db.start.reciclaalegre.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.LoginDTO;
import db.start.reciclaalegre.model.Usuario;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(JwtService jwtService, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public String gerarToken(LoginDTO loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.email(),
                loginDto.senha());

        Authentication auth = authManager.authenticate(authToken);
        Usuario usuario = (Usuario) auth.getPrincipal();

        return jwtService.gerarToken(usuario);
    }

}
