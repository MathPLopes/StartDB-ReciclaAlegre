package db.start.reciclaalegre.model;

import java.time.LocalDateTime;
import java.util.List;

import db.start.reciclaalegre.model.enums.StatusSolicitacao;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Perfil gerador;
    @ManyToOne
    private Perfil coletor;
    private LocalDateTime dataCriacao;
    @Embedded
    private Endereco endereco;
    @OneToMany
    private List<Material> materiais;
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao situacao;
    private String descricao;

}
