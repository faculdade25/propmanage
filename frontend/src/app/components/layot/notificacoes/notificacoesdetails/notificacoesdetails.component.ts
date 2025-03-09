import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-notificacoesdetails',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, CommonModule],
  templateUrl: './notificacoesdetails.component.html',
  styleUrl: './notificacoesdetails.component.scss'
})
export class NotificacoesdetailsComponent {

  constructor(public modalRef: MdbModalRef<NotificacoesdetailsComponent>) {}

  opcoes = [
    { value: 'opcao1', label: 'Opção 1' },
    { value: 'opcao2', label: 'Opção 2' },
    { value: 'opcao3', label: 'Opção 3' }
  ];

}
