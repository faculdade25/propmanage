package app.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquilinoRequestDTO {
    @NotNull
    private String nome;

    @NotNull
    private String sobrenome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String telefone;

    @NotNull
    private String cpf;

    @NotNull
    private String rg;

    private String profissao;

    private LocalDate nascimento;
}