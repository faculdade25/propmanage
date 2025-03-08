package app.entity.dto;

import app.entity.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class PagamentoDTO {
    private long id;
    private String titular;
    private Long idApartamento;
    private BigDecimal valor;
    private LocalDate vencimento;
    private StatusPagamento status;

    public PagamentoDTO(long id, String titular, Long idApartamento, BigDecimal valor, LocalDate vencimento, StatusPagamento status) {
        this.id = id;
        this.titular = titular;
        this.idApartamento = idApartamento;
        this.valor = valor;
        this.vencimento = vencimento;
        this.status = status;
    }
}
