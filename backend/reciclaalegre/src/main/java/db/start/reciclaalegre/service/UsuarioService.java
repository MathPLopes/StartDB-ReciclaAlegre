package db.start.reciclaalegre.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.dto.UsuarioUpdateDTO;
import db.start.reciclaalegre.model.Perfil;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import db.start.reciclaalegre.utils.mapper.EntidadeMapper;
import db.start.reciclaalegre.utils.usuario.UsuarioUtils;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioUtils usuarioUtils;
    private final EntidadeMapper entidadeMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            EntidadeMapper entidadeMapper,
            PasswordEncoder passwordEncoder,
            UsuarioUtils usuarioUtils) {

        this.usuarioRepository = usuarioRepository;
        this.usuarioUtils = usuarioUtils;
        this.entidadeMapper = entidadeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioUtils.validarUsuario(email);
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


    @PreAuthorize("hasAnyRole('COLETOR', 'GERADOR', 'ADMINISTRADOR')")
    public UsuarioResponseDTO perfilDeUsuario(String email) {
        return entidadeMapper.usuarioToDto(usuarioUtils.validarUsuario(email));
    }

    @PreAuthorize("hasAnyRole('COLETOR', 'GERADOR', 'ADMINISTRADOR')")
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAllByAtivoTrue()
        .stream()
        .map(us -> entidadeMapper.usuarioToDto(us)).toList();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('COLETOR', 'GERADOR', 'ADMINISTRADOR')")
    public void desabilitarUsuario(String email) {
        Usuario usuario = usuarioUtils.validarUsuario(email);
        usuario.setAtivo(false);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('COLETOR', 'GERADOR', 'ADMINISTRADOR')")
    public UsuarioResponseDTO atualizarUsuario(String email, UsuarioUpdateDTO updateDTO) {
        Usuario usuario = usuarioUtils.validarUsuario(email);

        usuarioUtils.validarCamposString(updateDTO.nome(), "nome");
        usuarioUtils.validarCamposString(updateDTO.senha(), "senha");
        usuarioUtils.validarCamposString(updateDTO.telefone(), "telefone");
        usuario.setSenha(passwordEncoder.encode(updateDTO.senha()));
        usuario.getPerfil().setNome(updateDTO.nome());
        usuario.getPerfil().setTelefone(updateDTO.telefone());
        usuario.getPerfil().setEndereco(updateDTO.endereco());
        
        return entidadeMapper.usuarioToDto(usuarioRepository.save(usuario));
    }

}
