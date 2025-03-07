package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contrato", indexes = {@Index(name = "idx_codigo_contrato", columnList = "codigoContrato")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(unique = true, nullable = false)
	private String codigoContrato;

	@NotNull
	private BigDecimal valorCondominio;

	@NotNull
	private BigDecimal valorIptu;

	@NotNull
	private BigDecimal valorInternet;

	@NotNull
	private BigDecimal valorAluguel;

	private int referenteAno;

	@NotNull
	private LocalDate entrada;

	private boolean ativo;

	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inquilino_id", nullable = false)
	@JsonIgnoreProperties({"contratos", "password"})
	private User inquilino;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apartamento_id", nullable = false)
	@JsonIgnoreProperties("contratos")
	private Apartamento apartamento;

	@NotNull
	private LocalDate dataCriacao;

	@NotNull
	private String titular;

	@NotNull
	private String processo;

	private LocalDate dataAceite;

	@Enumerated(EnumType.STRING)
	private StatusContrato status;
}