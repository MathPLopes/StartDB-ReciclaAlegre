package db.start.reciclaalegre.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import db.start.reciclaalegre.model.Endereco;
import db.start.reciclaalegre.model.enums.StatusSolicitacao;

public record SolicitacaoResponseDTO(
                Long id,
                String gerador,
                String coletor,
                @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                LocalDateTime dataCriacao,
                String descricao,
                Endereco endereco,
                StatusSolicitacao situacao,
                Set<MaterialDTO> materiais) {

}
