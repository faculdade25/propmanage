import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../../enviroments/enviroment';

interface Tenant {
  id: number;
  nome: string;
  email: string;
  telefone: string;
}

interface NewTenant {
  nome: string;
  sobrenome: string;
  email: string;
  telefone: string;
  cpf: string;
  rg: string;
  profissao: string;
  nascimento: string;
}

@Injectable({
  providedIn: 'root',
})
export class ContratosService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private cookieService: CookieService) {

    console.log(this.cookieService.get('acs'));
    console.log(this.getHeaders())

  }

  private getHeaders(): HttpHeaders {
    console.log(this.cookieService.get('acs'));
    return new HttpHeaders({
      Authorization: `Bearer ${this.cookieService.get('acs')}`,
    });
  }
  

  getTenants(): Observable<Tenant[]> {
    console.log(this.cookieService.get('acs'));
    return this.http.get<Tenant[]>(`${this.apiUrl}/predios/inquilinos`, { headers: this.getHeaders() });
  }

  createTenant(tenant: NewTenant): Observable<Tenant> {
    return this.http.post<Tenant>(`${this.apiUrl}/api/v1/user/new/inquilinos`, tenant, { headers: this.getHeaders() });
  }
}
