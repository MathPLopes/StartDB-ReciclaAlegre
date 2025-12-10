package db.start.reciclaalegre;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import db.start.reciclaalegre.controller.UsuarioRestController;
import db.start.reciclaalegre.dto.PerfilRequestDTO;
import db.start.reciclaalegre.dto.PerfilResponseDTO;
import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.enums.TipoUsuario;
import db.start.reciclaalegre.repository.UsuarioRepository;
import db.start.reciclaalegre.service.JwtService;
import db.start.reciclaalegre.service.UsuarioService;

@WebMvcTest(controllers =  UsuarioRestController.class)
public class UsuarioRestControllerTest {

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UsuarioService usuarioService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioRestController usuarioRestController;

    @Test
    public void deveCriarUsuario() throws Exception {

        Endereco endereco = new Endereco("98765432", "Rua numero 1", "123","Bairro", "Cidade", "Estado", "Pais");
        PerfilRequestDTO perfilEntrada = new PerfilRequestDTO("Usuario Teste", endereco, "21999998877");
        UsuarioRequestDTO usuarioEntrada = new UsuarioRequestDTO("teste1@email.com", passwordEncoder.encode("senha123"), TipoUsuario.GERADOR, perfilEntrada);

        PerfilResponseDTO perfilSaida = new PerfilResponseDTO("Usuario Teste", endereco, "21999998877");
        UsuarioResponseDTO usuarioSaida = new UsuarioResponseDTO(1L, "teste1@email.com", true, TipoUsuario.GERADOR, perfilSaida);

        Mockito.when(usuarioService.adicionarUsuario(usuarioEntrada)).thenReturn(usuarioSaida);

        ResponseEntity<UsuarioResponseDTO> resposta = usuarioRestController.cadastrarUsuario(usuarioEntrada);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("teste1@email.com", resposta.getBody().email());
        assertEquals(1L, resposta.getBody().id());
    }
}
