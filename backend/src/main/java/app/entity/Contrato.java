package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ManyToOne;

public class Contrato {

	private long id;
	private String identificador;
	private Double valor_condominio;
	private Double valor_iptu;
	private Double valor_internet;
	private Double valor_aluguel;
	private String entrada;
	private boolean status;
	private String pagamento;
	
	//elacao entre contrato, ap e cliente
	@ManyToOne
	@JsonIgnoreProperties("contratos")
	private Cliente cliente;
	
	@ManyToOne
	@JsonIgnoreProperties("contratos") 
	private Apartamento ap;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Apartamento getAp() {
		return ap;
	}
	public void setAp(Apartamento ap) {
		this.ap = ap;
	}

//getters e setters---------------------	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Double getValor_condominio() {
		return valor_condominio;
	}
	public void setValor_condominio(Double valor_condominio) {
		this.valor_condominio = valor_condominio;
	}
	public Double getValor_iptu() {
		return valor_iptu;
	}
	public void setValor_iptu(Double valor_iptu) {
		this.valor_iptu = valor_iptu;
	}
	public Double getValor_internet() {
		return valor_internet;
	}
	public void setValor_internet(Double valor_internet) {
		this.valor_internet = valor_internet;
	}
	public Double getValor_aluguel() {
		return valor_aluguel;
	}
	public void setValor_aluguel(Double valor_aluguel) {
		this.valor_aluguel = valor_aluguel;
	}
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
		
	

}
