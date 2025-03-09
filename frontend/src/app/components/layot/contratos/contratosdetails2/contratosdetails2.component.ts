import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-contratosdetails2',
  standalone: true,
  imports: [ MdbFormsModule, FormsModule],
  templateUrl: './contratosdetails2.component.html',
  styleUrl: './contratosdetails2.component.scss'
})
export class Contratosdetails2Component {

  opcoes = [
    { value: 'opcao1', label: 'Opção 1' },
    { value: 'opcao2', label: 'Opção 2' },
    { value: 'opcao3', label: 'Opção 3' }
  ];


}
