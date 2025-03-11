export interface ApartamentoDTO {
    id: number;
    apnum: number;
    status: StatusApartamento;
}

export enum StatusApartamento {
    OCUPADO = 'OCUPADO',
    VAGO = 'VAGO', 
    MANUTENCAO = 'MANUTENCAO'
}