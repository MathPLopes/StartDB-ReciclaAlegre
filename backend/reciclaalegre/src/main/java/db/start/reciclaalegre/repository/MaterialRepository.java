package db.start.reciclaalegre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import db.start.reciclaalegre.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{

}
