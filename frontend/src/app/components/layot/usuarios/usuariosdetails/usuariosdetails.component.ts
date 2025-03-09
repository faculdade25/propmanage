import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider'; 
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-usuariosdetails',
  standalone: true,
  imports: [ 
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDividerModule // <– Adicione aqui
  ],
  templateUrl: './usuariosdetails.component.html',
  styleUrls: ['./usuariosdetails.component.scss']
})
export class UsuariosdetailsComponent {
  constructor(
    public dialogRef: MatDialogRef<UsuariosdetailsComponent>
  ) {}

  fechar(): void {
    this.dialogRef.close();
  }
}