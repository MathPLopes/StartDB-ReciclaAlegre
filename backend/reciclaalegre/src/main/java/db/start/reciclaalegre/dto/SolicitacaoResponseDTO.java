package db.start.reciclaalegre.dto;

import java.time.LocalDateTime;
import java.util.Set;

import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.enums.StatusSolicitacao;

public record SolicitacaoResponseDTO(
                Long id,
                String gerador,
                LocalDateTime dataCriacao,
                String descricao,
                Endereco endereco,
                StatusSolicitacao situacao,
                Set<MaterialDTO> materiais) {

}
