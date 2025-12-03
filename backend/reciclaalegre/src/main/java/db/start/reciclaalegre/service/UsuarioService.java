package db.start.reciclaalegre.service;

import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import db.start.reciclaalegre.utils.mapper.UsuarioMapper;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO adicionarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(dto));
        return usuarioMapper.toDto(usuario);
    }

}
