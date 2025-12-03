package db.start.reciclaalegre.utils.mapper;

import org.mapstruct.Mapper;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDTO toDto(Usuario usuario);

    Usuario toEntity(UsuarioRequestDTO dto);
}
