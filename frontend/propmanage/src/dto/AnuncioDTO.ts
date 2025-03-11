export interface AnuncioDTO {
    id: number;
    titulo: string;
    descricao: string;
    status: StatusAviso;
    predioId: number;
    data: Date;
    dataCriacao: Date;
}

export enum StatusAviso {
    CANCELADO = 'CANCELADO',
    PENDENTE = 'PENDENTE',
    RESOLVIDO = 'RESOLVIDO',
    PREVISTO = 'PREVISTO'
}