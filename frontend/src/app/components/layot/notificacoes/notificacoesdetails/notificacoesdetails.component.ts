import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog'; // Novo import
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select'; // Para as opções
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';

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
  constructor(
    public dialogRef: MatDialogRef<NotificacoesdetailsComponent> // Novo tipo
  ) { }

  opcoes = [
    { value: 'opcao1', label: 'Opção 1' },
    { value: 'opcao2', label: 'Opção 2' },
    { value: 'opcao3', label: 'Opção 3' }
  ];
}