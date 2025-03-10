import { Injectable } from '@angular/core';
import { ApartamentoDTO } from '../../dto/ApartamentoDTO';
import { environment } from '../../../enviroments/enviroment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApartamentosService {

  apiUrl = environment.apiUrl + "/predios/apartamentos";

  constructor(private http: HttpClient) { }

  allApartamentos(): Observable<ApartamentoDTO[]> {
    return this.http.get<ApartamentoDTO[]>(this.apiUrl);
  }

}
