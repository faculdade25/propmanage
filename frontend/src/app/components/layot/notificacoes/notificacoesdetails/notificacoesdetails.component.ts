import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { environment } from '../../../../../../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { jwtDecode } from 'jwt-decode';
import { AnuncioDTO } from '../../../../../dto/AnuncioDTO';

interface CustomJwtPayload {
  buildingId: number;
}

@Component({
  selector: 'app-notificacoesdetails',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDividerModule,
  ],
  templateUrl: './notificacoesdetails.component.html',
  styleUrls: ['./notificacoesdetails.component.scss']
})
export class NotificacoesdetailsComponent {

  apiUrl = environment.apiUrl;
  selectedStatus: string = "PENDENTE";
  message: string = "";
  date: Date = new Date();
  anuncio!: AnuncioDTO;

  constructor(
    private http: HttpClient, 
    private cookieService: CookieService,
    public dialogRef: MatDialogRef<NotificacoesdetailsComponent>
  ) { }

  // Método para obter o token do cookie
  getCookie(): string {
    return this.cookieService.get('acs');
  }

  // Método para extrair o buildingId do token
  getPredioId(): number {
    const token = this.getCookie();
    const decoded = jwtDecode<CustomJwtPayload>(token);
    return decoded.buildingId;
  }

  enviarAnuncio() {
    const buildingID = this.getPredioId(); // Obtém o ID do prédio
  
    // Garante que `this.date` seja um objeto Date
    const dataObj = new Date(this.date);
  
    if (isNaN(dataObj.getTime())) {  
      console.error("Data inválida:", this.date);
      return;
    }
  
    const body = {
      titulo: this.message,
      descricao: "",
      status: this.selectedStatus,
      data: dataObj.toISOString().split('T')[0] + "T00:00:00" // Formata a data corretamente
    };


  
    const url = `${this.apiUrl}/api/anuncios/${buildingID}/save`;
  
    this.http.post(url, body).subscribe({
      next: (response) => {
        console.log("Anúncio enviado com sucesso!", response);
        this.dialogRef.close(); 
      },
      error: (error) => {
        console.error("Erro ao enviar anúncio:", error);
        this.dialogRef.close(); 
      }
    });
  }
}