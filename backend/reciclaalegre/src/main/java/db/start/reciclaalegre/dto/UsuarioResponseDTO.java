package db.start.reciclaalegre.dto;

public record UsuarioResponseDTO(
    Long id, 
    String nome, 
    String email, 
    String telefone, 
    Boolean isAtivo) {

}
