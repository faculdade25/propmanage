import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-apartamentosdetails',
  standalone: true,
  imports: [MatDialogModule, ReactiveFormsModule, MatFormFieldModule], 
  templateUrl: './apartamentosdetails.component.html',
  styleUrls: ['./apartamentosdetails.component.scss']
})
export class ApartamentosdetailsComponent {
  constructor(public dialogRef: MatDialogRef<ApartamentosdetailsComponent>) {}

  closeModal() {
    this.dialogRef.close(); 
  }
}