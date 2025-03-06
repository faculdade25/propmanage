package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contrato")
@Getter
@Setter
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String identificador;
	private Double valorCondominio;
	private Double valorIptu;
	private Double valorInternet;
	private Double valorAluguel;
	private String entrada;
	private boolean status;
	private String pagamento;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("contratos")
	private User user;

	@ManyToOne
	@JoinColumn(name = "ap_id", nullable = false)
	@JsonIgnoreProperties("contratos")
	private Apartamento ap;
}