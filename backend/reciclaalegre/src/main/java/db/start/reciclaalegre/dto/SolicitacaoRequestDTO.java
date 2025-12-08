package db.start.reciclaalegre.dto;

import java.util.Set;

public record SolicitacaoRequestDTO(
        Set<MaterialDTO> materiais,
        String descricao) {

}
