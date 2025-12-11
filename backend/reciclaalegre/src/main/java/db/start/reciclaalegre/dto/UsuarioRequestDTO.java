package db.start.reciclaalegre.dto;

import db.start.reciclaalegre.model.enums.TipoUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "O campo email não deve estar em branco")
        @Email(message = "verifique o campo email")
        String email,

        @NotBlank(message = "O campo senha não")
        @Size(min = 8, max = 20, message = "a senha deve conter entre 8 e 20 caracteres")
        String senha,

        TipoUsuario tipoUsuario,

        @Valid
        PerfilRequestDTO perfil) {
}
