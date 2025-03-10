import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../enviroments/enviroment';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { jwtDecode } from 'jwt-decode';
import { AnuncioDTO } from '../../dto/AnuncioDTO';

interface CustomJwtPayload {
  buildingId: number;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  getCookie(): string {
    return this.cookieService.get('acs');
  }

  getPredioId(): number {
    const token = this.getCookie();
    console.log(token);
    const decoded = jwtDecode<CustomJwtPayload>(token);
    console.log(decoded);
    return decoded.buildingId;
  }

  apiUrl = environment.apiUrl + "/api/anuncios/";
  
  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getNotifications(): Observable<AnuncioDTO[]> {
    return this.http.get<AnuncioDTO[]>(this.apiUrl+this.getPredioId()+"/list");
  }

}
