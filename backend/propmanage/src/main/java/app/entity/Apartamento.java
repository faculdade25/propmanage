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

	private int apnum;

	@Enumerated(EnumType.STRING)
	private StatusApartamento status;

	@ManyToOne
	@JoinColumn(name = "predio_id", nullable = false)
	private Predio predio;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contratos"})
	@OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contrato> contratos;

	@Override
	public String toString() {
		return "Apartamento{id=" + id + ", apnum=" + apnum + ", status=" + status + "}";
	}
}
