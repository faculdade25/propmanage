import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider'; 
import { MatFormFieldModule } from '@angular/material/form-field';
import { HttpClient } from '@angular/common/http';
import { InquilinoRequestDTO } from '../../../../../dto/requests/InquilinoDTO';
import { environment } from '../../../../../../enviroments/enviroment';

@Component({
  selector: 'app-usuariosdetails',
  standalone: true,
  imports: [ 
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDividerModule 
  ],
  templateUrl: './usuariosdetails.component.html',
  styleUrls: ['./usuariosdetails.component.scss']
})
export class UsuariosdetailsComponent {

  inquilino: InquilinoRequestDTO = new InquilinoRequestDTO();

  apiUrl = environment.apiUrl;

  constructor(
    private http: HttpClient,
    public dialogRef: MatDialogRef<UsuariosdetailsComponent>
  ) {}

  fechar(): void {
    this.dialogRef.close();
  }

  enviarInquilino(): void {
    const url = `${this.apiUrl}/api/v1/user/new/inquilinos`;

    this.http.post(url, this.inquilino).subscribe({
      next: (response) => {
        console.log("Inquilino cadastrado com sucesso!", response);
        this.dialogRef.close(); // Fecha o modal ao concluir
      },
      error: (error) => {
        console.error("Erro ao cadastrar inquilino:", error);
      }
    });
  }
}