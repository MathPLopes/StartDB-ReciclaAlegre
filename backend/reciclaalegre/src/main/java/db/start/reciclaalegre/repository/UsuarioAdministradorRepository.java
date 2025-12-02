package db.start.reciclaalegre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import db.start.reciclaalegre.model.UsuarioAdministrador;

public interface UsuarioAdministradorRepository extends JpaRepository<UsuarioAdministrador, Long> {
    
}
