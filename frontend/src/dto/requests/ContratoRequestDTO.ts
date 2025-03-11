export class NovoContratoDTO {
    apnum!: number; 
    titularId!: number; 
    valorCondominio!: number;
    valorIptu!: number;
    valorInternet!: number;
    valorAluguel!: number;
    entrada!: Date;
    processo?: string;
    referente?: number;
    dataAceite?: Date;
    status?: StatusContrato;
}

export enum StatusContrato {
    DEFERIDO = 'DEFERIDO',
    INDEFERIDO = 'INDEFERIDO',
    CANCELADO = 'CANCELADO',
    VALIDO = 'VALIDO',
    ATUAL = 'ATUAL'
}