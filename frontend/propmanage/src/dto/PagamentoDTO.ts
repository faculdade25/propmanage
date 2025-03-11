export interface PagamentoDTO {
    id: number;
    titular: string;
    idApartamento: number;
    valor: number;
    vencimento: Date;
    status: StatusPagamento;
}

export enum StatusPagamento {
    PENDENTE = 'PENDENTE',
    PAGO = 'PAGO',
    A_VENCER = 'A VENCER',
    FUTURO = 'INDISPONIVEL',
    ATRASADO = 'ATRASADO'
}