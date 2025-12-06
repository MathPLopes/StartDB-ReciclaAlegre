package db.start.reciclaalegre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.Perfil;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.model.enums.TipoUsuario;
import db.start.reciclaalegre.repository.UsuarioRepository;

@SpringBootApplication
public class ReciclaalegreApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReciclaalegreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario1 = new Usuario("email1@email.com", "senha123", true, TipoUsuario.GERADOR, null);
		Perfil perfil1 = new Perfil(null, "Matheus",
				new Endereco("876456000",
						"Rua1", "123", "Centro", "Cidade1", "Estado1", "Brasa"),
				"44987456321", null, usuario1);
		usuario1.setPerfil(perfil1);

		Usuario usuario2 = new Usuario("email2@email.com", "senha123", true, TipoUsuario.COLETOR, null);
		Perfil perfil2 = new Perfil(null, "Cristian",
				new Endereco("876456000",
						"Rua1", "123", "Centro", "Cidade1", "Estado1", "Brasa"),
				"44987456321", null, usuario2);
		usuario2.setPerfil(perfil2);

		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);
	}
}
