package db.start.reciclaalegre.dto;

import db.start.reciclaalegre.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
    @Size(min = 8, max = 20)
    String senha,

    String nome, 

    @Pattern(regexp = "\\d{11}", message = "o campo telefone deve conter 11 digitos")
    String telefone,
    
    @Valid
    Endereco endereco
) {

}
