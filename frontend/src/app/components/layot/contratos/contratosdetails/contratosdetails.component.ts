import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';
import { NotificacoesdetailsComponent } from '../../notificacoes/notificacoesdetails/notificacoesdetails.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-contratosdetails',
  standalone: true,
  imports: [ FormsModule, CommonModule, MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule],
  templateUrl: './contratosdetails.component.html',
  styleUrl: './contratosdetails.component.scss'
})
export class ContratosdetailsComponent {
  
  constructor(public modalRef: MatDialogRef<NotificacoesdetailsComponent>) {}

  opcoes = [
    { value: 'opcao1', label: 'Opção 1' },
    { value: 'opcao2', label: 'Opção 2' },
    { value: 'opcao3', label: 'Opção 3' }
  ];
}





