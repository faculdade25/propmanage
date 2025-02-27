package app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Anuncio {

	private long id;
	private String nome;
	private String dscritivo;
	private LocalDateTime timestamp;
	
//getters e setters---------------------	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDscritivo() {
		return dscritivo;
	}
	public void setDscritivo(String dscritivo) {
		this.dscritivo = dscritivo;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	
	

}
