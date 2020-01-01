package estudo.microservices.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Microservice implements AbstractEntity {

    public Microservice(String nome) {
        this.nome = nome;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @NotNull(message = "The field 'nome' is mandatory")
    @Column(nullable = false)
    private String nome;

    @Override
    public Long getId() {
        return id;
    }
}
