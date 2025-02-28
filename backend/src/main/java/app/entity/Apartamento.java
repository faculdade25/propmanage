package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "apartamento")
@Getter
@Setter
public class Apartamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long apnum;
	private String status;
	private String fotos;

	// Relação entre Apartamento e Contrato
	@OneToMany(mappedBy = "ap", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("ap")
	private List<Contrato> contratos;
}
