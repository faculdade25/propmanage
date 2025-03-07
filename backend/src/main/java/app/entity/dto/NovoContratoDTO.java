package app.entity.dto;

import app.entity.StatusApartamento;
import app.entity.StatusContrato;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class NovoContratoDTO {
    @NotNull
    private long apnum; // Número do apartamento

    @NotNull
    private long titularId; // ID do titular

    @NotNull
    private BigDecimal valorCondominio;

    @NotNull
    private BigDecimal valorIptu;

    @NotNull
    private BigDecimal valorInternet;

    @NotNull
    private BigDecimal valorAluguel;

    @NotNull
    private LocalDate entrada;

    private String processo;

    private int referente;

    private LocalDate dataAceite;

    private StatusContrato status;
}