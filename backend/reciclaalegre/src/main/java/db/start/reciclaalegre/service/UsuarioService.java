package db.start.reciclaalegre.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.model.Perfil;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.PerfilRepository;
import db.start.reciclaalegre.repository.UsuarioRepository;
import db.start.reciclaalegre.utils.mapper.EntidadeMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final EntidadeMapper entidadeMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository,
            EntidadeMapper entidadeMapper,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.entidadeMapper = entidadeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO adicionarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = entidadeMapper.usuarioToEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        Perfil perfil = entidadeMapper.perfilToEntity(dto.perfil());
        usuario.setPerfil(perfil);
        perfil.setUsuario(usuario);
        usuarioRepository.save(usuario);
        return entidadeMapper.usuarioToDto(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public UsuarioResponseDTO perfilDeUsuario(String usuarioEmail) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return entidadeMapper.usuarioToDto(usuario);
    }

}
