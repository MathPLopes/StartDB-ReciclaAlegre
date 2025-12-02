package db.start.reciclaalegre.model;

import db.start.reciclaalegre.model.enums.QuantidadeMaterial;
import db.start.reciclaalegre.model.enums.TipoMaterial;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoMaterial tipoMaterial;
    private QuantidadeMaterial quantidadeMaterial;
}
