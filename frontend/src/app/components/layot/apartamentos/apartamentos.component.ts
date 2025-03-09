import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ApartamentosdetailsComponent } from './apartamentosdetails/apartamentosdetails.component';

@Component({
  selector: 'app-apartamentos',
  standalone: true,
  imports: [MatDialogModule],
  templateUrl: './apartamentos.component.html',
  styleUrl: './apartamentos.component.scss'
})
export class ApartamentosComponent {
  constructor(private dialog: MatDialog) {}

  openModal() {
    this.dialog.open(ApartamentosdetailsComponent, {
      panelClass: ['max-w-2xl', 'mx-auto'], 
      width: '90%',
      height: 'auto'
    });
  }
}