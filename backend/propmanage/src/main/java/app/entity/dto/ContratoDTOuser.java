package app.entity.dto;

import app.entity.StatusContrato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTOuser {

    private LocalDate dataCriacao;
    private String processo;
    private int referente;
    private LocalDate dataAceite;
    private StatusContrato status;

}
