package app.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquilinoDTO {
    private String nome;
    private String email;
    private String telefone;

    public InquilinoDTO(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}
