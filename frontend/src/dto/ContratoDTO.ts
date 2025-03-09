export interface ContratoDTOuser {
    dataCriacao: Date;
    processo: string;
    referente: number;
    dataAceite: Date;
    status: StatusContrato;
}

export enum StatusContrato {
    DEFERIDO, INDEFERIDO, CANCELADO, VALIDO, ATUALIZADO
}