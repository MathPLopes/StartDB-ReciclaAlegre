package db.start.reciclaalegre.model;

import java.time.LocalDateTime;
import java.util.List;

import db.start.reciclaalegre.model.enums.StatusSolicitacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Usuario gerador;
    private Usuario coletor;
    private LocalDateTime dataCriacao;
    private Endereco endereco;
    private List<Material> materiais;
    private StatusSolicitacao situacao;
    private String descricao;

}
