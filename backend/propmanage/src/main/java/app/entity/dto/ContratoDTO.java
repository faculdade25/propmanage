package app.entity.dto;

import app.entity.Contrato;
import app.entity.StatusContrato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTO {
    private long id;
    private String codigoContrato;
    private LocalDate dataCriacao;
    private String titular;
    private int apNum;
    private String processo;
    private int referenteAno;
    private LocalDate dataAceite;
    private StatusContrato status;
    private BigDecimal valorAluguel;
    private BigDecimal valorCondominio;
    private BigDecimal valorIptu;
    private BigDecimal valorInternet;
    private boolean ativo;

    public ContratoDTO(Contrato contrato) {
        this.id = contrato.getId();
        this.codigoContrato = contrato.getCodigoContrato();
        this.dataCriacao = contrato.getDataCriacao();
        this.titular = contrato.getTitular();
        this.apNum = contrato.getApartamento().getApnum();
        this.processo = contrato.getProcesso();
        this.referenteAno = contrato.getReferenteAno();
        this.dataAceite = contrato.getDataAceite();
        this.status = contrato.getStatus();
        this.valorAluguel = contrato.getValorAluguel();
        this.valorCondominio = contrato.getValorCondominio();
        this.valorIptu = contrato.getValorIptu();
        this.valorInternet = contrato.getValorInternet();
        this.ativo = contrato.isAtivo();
    }
}
