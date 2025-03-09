import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { ContratoDTOuser } from '../../dto/ContratoDTO';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ContractsService {
  
    apiUrl = environment.apiUrl + "/api/contratos";
  
    constructor(private http: HttpClient) { }

    contratoAtual(): Observable <ContratoDTOuser>{
      return this.http.get<ContratoDTOuser>(this.apiUrl + "/user/contrato/atual");
    }

    allContratos(): Observable <ContratoDTOuser[]>{
      return this.http.get<ContratoDTOuser[]>(this.apiUrl + "/user/contratos");
    }

}
