package db.start.reciclaalegre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import db.start.reciclaalegre.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);  
}
