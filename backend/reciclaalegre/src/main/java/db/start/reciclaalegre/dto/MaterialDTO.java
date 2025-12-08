package db.start.reciclaalegre.dto;

import db.start.reciclaalegre.model.enums.QuantidadeMaterial;
import db.start.reciclaalegre.model.enums.TipoMaterial;

public record MaterialDTO(
        TipoMaterial tipoMaterial,
        QuantidadeMaterial quantidadeMaterial) {

}
