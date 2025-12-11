package db.start.reciclaalegre.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import db.start.reciclaalegre.dto.SolicitacaoRequestDTO;
import db.start.reciclaalegre.dto.SolicitacaoResponseDTO;
import db.start.reciclaalegre.model.Solicitacao;

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    @Mapping(target = "gerador", expression = "java(solicitacao.getGerador().getNome())")
    @Mapping(target = "coletor", expression = "java(solicitacao.getColetor().getNome())")
    SolicitacaoResponseDTO toDto(Solicitacao solicitacao);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gerador", ignore = true)
    @Mapping(target = "coletor", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "situacao", ignore = true)
    @Mapping(target = "materiais", ignore = true)
    Solicitacao toEntity(SolicitacaoRequestDTO dto);
}
