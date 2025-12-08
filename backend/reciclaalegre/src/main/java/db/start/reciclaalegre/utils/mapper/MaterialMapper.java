package db.start.reciclaalegre.utils.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import db.start.reciclaalegre.dto.MaterialDTO;
import db.start.reciclaalegre.model.Material;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    Set<MaterialDTO> toDto(Set<Material> material);

    Set<Material> toEntity(Set<MaterialDTO> dto);

    @Mapping(target = "id", ignore = true)
    Material toEntity(MaterialDTO dto);
}
