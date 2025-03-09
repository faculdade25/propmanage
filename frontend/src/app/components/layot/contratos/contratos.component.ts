import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ContratosdetailsComponent } from './contratosdetails/contratosdetails.component';

@Component({
  selector: 'app-contratos',
  standalone: true,
  imports: [MatDialogModule],
  templateUrl: './contratos.component.html',
  styleUrls: ['./contratos.component.scss']
})
export class ContratosComponent {

  constructor(private dialog: MatDialog) {} 

  openModal() {
    this.dialog.open(ContratosdetailsComponent, {
      width: '600px', 
      maxWidth: '90vw',
      panelClass: 'custom-dialog-container', 
    });
  }
}