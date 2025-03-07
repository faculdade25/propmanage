package app.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "anuncios")
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false, length = 1000)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private StatusAviso status;

	@Column(name = "predio_id", nullable = false)
	private Long predioId;

	private Date data;

	@Column(nullable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Anuncio() {
	}

	public Anuncio(String titulo, String descricao, Long predioId, Date data, StatusAviso status) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.predioId = predioId;
		this.data = data;
		this.status = status;
	}
}