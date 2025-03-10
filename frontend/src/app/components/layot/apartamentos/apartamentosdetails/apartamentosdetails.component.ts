import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommonModule, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms'; 

import { ApartamentoDTO, StatusApartamento } from '../../../../../dto/ApartamentoDTO';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../enviroments/enviroment';

@Component({
  selector: 'app-apartamentosdetails',
  standalone: true,
  imports: [CommonModule, NgIf, FormsModule], 
  templateUrl: './apartamentosdetails.component.html',
  styleUrls: ['./apartamentosdetails.component.scss']
})
export class ApartamentosdetailsComponent {
  apartamento: ApartamentoDTO = {} as ApartamentoDTO; 

  apiUrl = environment.apiUrl + "/predios";

  apiUrl2 = environment.apiUrl + "/apartamento";

  constructor(
    public dialogRef: MatDialogRef<ApartamentosdetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ApartamentoDTO | null,
    private http: HttpClient
  ) {
    if (data) {
      this.apartamento = data;
    }
  }

  novoApartamento() {
    this.apartamento.status = StatusApartamento.VAGO;
    this.http.post(this.apiUrl + "/new/apartamento", this.apartamento).subscribe((response) => {
      this.closeModal();
    });
  }

  editApartamento() {
    if (!this.apartamento || !this.apartamento.id || !this.apartamento.apnum) {
      console.error("Erro: Apartamento inválido!");
      return;
    }
    const url = `${this.apiUrl}/edit/apartamento/${encodeURIComponent(this.apartamento.id)}/${encodeURIComponent(this.apartamento.apnum)}`;
    this.http.put(url, {}).subscribe(
      (response) => {
        console.log("Apartamento editado com sucesso:", response);
        this.closeModal();
      },
      (error) => {
        console.error("Erro ao editar apartamento:", error);
      }
    );
  }

  deleteApartamento(){
    this.http.delete(this.apiUrl2 + "/delete/" + this.apartamento.id).subscribe((response) => {
      this.closeModal();
    });
  }

  closeModal() {
    this.dialogRef.close();
  }
}