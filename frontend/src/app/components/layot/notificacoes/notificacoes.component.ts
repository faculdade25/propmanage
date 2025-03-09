import { Component } from '@angular/core';
import { NotificacoesdetailsComponent } from './notificacoesdetails/notificacoesdetails.component';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog'; // <-- Nova importação

@Component({
  selector: 'app-notificacoes',
  standalone: true,
  imports: [ MatDialogModule],
  templateUrl: './notificacoes.component.html',
  styleUrl: './notificacoes.component.scss'
})
export class NotificacoesComponent {
  constructor(private dialog: MatDialog) {} // <-- Nova injeção

  openModal() {
    this.dialog.open(NotificacoesdetailsComponent, { // <-- Nova sintaxe
      panelClass: ['max-w-2xl', 'mx-auto'], // Estilo Tailwind
      width: '90%', // Largura personalizada
      height: 'auto'
    });
  }
}