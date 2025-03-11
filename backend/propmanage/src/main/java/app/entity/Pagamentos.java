package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "pagamentos")
@NoArgsConstructor
public class Pagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    @JsonIgnoreProperties("pagamentos")
    private Contrato contrato;

    @NotNull
    private String titular;

    @NotNull
    private Long idApartamento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
