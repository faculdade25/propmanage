package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.OneToMany;

public class Apartamento {

	private long apnum;
	private String status;
	private String fotos;
	
	//relacao entre ap e contrato
	@OneToMany(mappedBy = "ap")	
	@JsonIgnoreProperties("ap")
	private List<Contrato> contratos;
	
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
//getters e setters---------------------	
	
	public long getApnum() {
		return apnum;
	}
	public void setApnum(long apnum) {
		this.apnum = apnum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFotos() {
		return fotos;
	}
	public void setFotos(String fotos) {
		this.fotos = fotos;
	}
	
	
	
}
