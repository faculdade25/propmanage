import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../enviroments/enviroment';
import { Observable } from 'rxjs';
import { PagamentoDTO } from '../../dto/PagamentoDTO';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

  apiUrl = environment.apiUrl + "/payments";

  constructor(private http: HttpClient) { }

   //users

  proximoPGTO(): Observable <PagamentoDTO>{
    return this.http.get<PagamentoDTO>(this.apiUrl + "/proximo");
  }

  next6PGTO(): Observable <PagamentoDTO[]>{
    return this.http.get<PagamentoDTO[]>(this.apiUrl + "/ultimos");
  }

  allPGTO(): Observable <PagamentoDTO[]>{
    return this.http.get<PagamentoDTO[]>(this.apiUrl + "/todos");
  }

  //admin
  totalSaldo(): Observable <number>{
    return this.http.get<number>(this.apiUrl + "/total-pagos");
  }

  totalSaldoPendente(): Observable <number>{
    return this.http.get<number>(this.apiUrl + "/total-pendentes");
  }

  allPagamentosPendente(): Observable <PagamentoDTO[]>{ 
    return this.http.get<PagamentoDTO[]>(this.apiUrl + "/pendentes");
  }



}
