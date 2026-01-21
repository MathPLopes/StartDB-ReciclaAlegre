package db.start.reciclaalegre.utils.usuario;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import db.start.reciclaalegre.model.Solicitacao;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Component
public class UsuarioUtils {

    private final UsuarioRepository usuarioRepository;

    public UsuarioUtils(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario validarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    public void validarCamposString(String valor, String campo) {
        if (valor.isBlank() || valor == null) {
            throw new IllegalArgumentException("Preencha corretamente o campo: " + campo);
        }
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void validarPermissao(Usuario usuario, Solicitacao solicitacao) {
        if (!solicitacao.getGerador().getUsuario().getId().equals(usuario.getId())) {
            throw new AccessDeniedException("Usuário não tem permissão para atualizar esta solicitação");
        }
    }

}
