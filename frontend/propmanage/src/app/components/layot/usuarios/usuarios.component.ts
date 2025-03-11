import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'; // <– Alteração aqui
import { UsuariosdetailsComponent } from './usuariosdetails/usuariosdetails.component';
import { InquilinoDTO } from '../../../../dto/requests/InquilinoMinDTO';
import { environment } from '../../../../../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [NgFor],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.scss'
})
export class UsuariosComponent {
  constructor(private dialog: MatDialog, private http: HttpClient) {
    this.loadUsuarios();
  }

  usuarios: InquilinoDTO[] = [];
  apiUrl = environment.apiUrl;

  openModal() {
    this.dialog.open(UsuariosdetailsComponent, { 
      panelClass: ['w-full', 'max-w-2xl', 'mx-auto'] 
    });
  }

  loadUsuarios(){
    this.http.get<InquilinoDTO[]>(this.apiUrl + "/predios/inquilinos").subscribe(
      (data) => {
        this.usuarios = data;
      }
    );
  }
}