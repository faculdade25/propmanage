import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'; // <– Alteração aqui
import { UsuariosdetailsComponent } from './usuariosdetails/usuariosdetails.component';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [ ],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.scss'
})
export class UsuariosComponent {
  constructor(private dialog: MatDialog) {}

  openModal() {
    this.dialog.open(UsuariosdetailsComponent, { 
      panelClass: ['w-full', 'max-w-2xl', 'mx-auto'] 
    });
  }
}