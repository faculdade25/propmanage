package app.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "predio")
@Getter
@Setter
public class Predio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    private String descricao;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "predio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("predio")
    private List<Apartamento> apartamentos;
}