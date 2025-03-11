export interface ContratoDTOFull {
    id: number;
    codigoContrato: string;
    dataCriacao: Date;
    titular: number;
    apNum: number;
    processo: string;
    referenteAno: number;
    dataAceite: Date;
    status: StatusContrato;
    valorAluguel: number;
    valorCondominio: number;
    valorIptu: number;
    valorInternet: number;
    ativo: boolean;
}

export enum StatusContrato {
    DEFERIDO = 'DEFERIDO',
    INDEFERIDO = 'INDEFERIDO',
    CANCELADO = 'CANCELADO',
    VALIDO = 'VALIDO',
    ATUAL = 'ATUAL'
}
