package db.start.reciclaalegre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.Perfil;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.model.enums.TipoUsuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void deveSalvarUsuarioNoRepository() throws Exception {
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        usuario.setEmail("email1@email.com");
        usuario.setSenha(passwordEncoder.encode("senha123"));
        usuario.setAtivo(true);
        usuario.setTipoUsuario(TipoUsuario.GERADOR);
        perfil.setEndereco(new Endereco("99999999", "Rua rua1", "200", "lagoinha", "cidade", "estado", "Brasil"));
        perfil.setNome("Usuario Teste");
        perfil.setTelefone("21999999999");
        perfil.setSolicitacoes(new ArrayList<>());
        perfil.setUsuario(usuario);
        usuario.setPerfil(perfil);


        Usuario salvo = usuarioRepository.save(usuario);

        Usuario resultado = usuarioRepository.findById(salvo.getId()).orElseThrow(() -> new EntityNotFoundException());
        
        assertEquals(resultado, salvo);
        assertEquals(salvo.getEmail(), usuario.getEmail());
    }
}
