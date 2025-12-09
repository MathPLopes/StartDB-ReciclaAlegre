package db.start.reciclaalegre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.Perfil;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.model.enums.TipoUsuario;
import db.start.reciclaalegre.repository.PerfilRepository;
import db.start.reciclaalegre.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PerfilRepository perfilRepository;


    @Test
    public void deveSalvarUsuarioNoRepository() throws Exception {
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        usuario.setId(1L);
        usuario.setEmail("email1@email.com");
        usuario.setSenha("senha123");
        usuario.setAtivo(true);
        usuario.setTipoUsuario(TipoUsuario.GERADOR);
        perfil.setEndereco(new Endereco("99999999", "Rua rua1", "200", "lagoinha", "cidade", "estado", "Brasil"));
        perfil.setNome("Usuario Teste");
        perfil.setTelefone("21999999999");
        perfil.setSolicitacoes(new ArrayList<>());
        perfil.setUsuario(usuario);
        usuario.setPerfil(perfil);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioRepository.save(usuario);
        assertEquals(usuario.getEmail(), result.getEmail());
        assertEquals(usuario.getSenha(), result.getSenha());
        assertEquals(perfil, result.getPerfil());
        assertNotNull(result.getId());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}
