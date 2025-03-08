package app.entity.dto;

import app.entity.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoResumoDTO {

    private Long idApartamento;
    private LocalDate vencimento;
    private BigDecimal valor;
    private StatusPagamento status;

    public PagamentoResumoDTO(Long idApartamento, LocalDate vencimento, BigDecimal valor, StatusPagamento status) {
        this.idApartamento = idApartamento;
        this.vencimento = vencimento;
        this.valor = valor;
        this.status = status;
    }

}
