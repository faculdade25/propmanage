package app.entity.dto;

import app.entity.StatusApartamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApartamentoDTO {
    private long id;
    private int apnum;
    private StatusApartamento status;

    public ApartamentoDTO(long id, int apnum, StatusApartamento status) {
        this.id = id;
        this.apnum = apnum;
        this.status = status;
    }
}