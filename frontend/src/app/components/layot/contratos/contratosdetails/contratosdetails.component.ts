import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-contratosdetails',
  standalone: true,
  imports: [ MdbFormsModule, FormsModule, CommonModule],
  templateUrl: './contratosdetails.component.html',
  styleUrl: './contratosdetails.component.scss'
})
export class ContratosdetailsComponent {
  constructor(public modalRef: MdbModalRef<ContratosdetailsComponent>) {}

  opcoes = [
    { value: 'opcao1', label: 'Opção 1' },
    { value: 'opcao2', label: 'Opção 2' },
    { value: 'opcao3', label: 'Opção 3' }
  ];
}





