package db.start.reciclaalegre.service;

import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.LoginDto;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public String gerarToken(LoginDto loginDto) {
        Usuario usuario = usuarioRepository.findByEmail(loginDto.email()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return jwtService.gerarToken(usuario);
    }

}
