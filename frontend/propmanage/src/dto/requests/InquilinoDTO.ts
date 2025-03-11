export class InquilinoRequestDTO {
    nome!: string;
    sobrenome!: string;
    email!: string;
    telefone!: string;
    cpf!: string;
    rg!: string;
    profissao?: string;
    nascimento?: Date;
}