import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usuariosdetails',
  standalone: true,
  imports: [ MdbFormsModule, FormsModule, CommonModule],
  templateUrl: './usuariosdetails.component.html',
  styleUrl: './usuariosdetails.component.scss'
})
export class UsuariosdetailsComponent {
  constructor(public modalRef: MdbModalRef<UsuariosdetailsComponent>) {}


}
