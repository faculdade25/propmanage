import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ContratosdetailsComponent } from './contratosdetails/contratosdetails.component';
import { CommonModule } from '@angular/common';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-contratos',
  standalone: true,
  imports: [MatDialogModule, CommonModule],
  templateUrl: './contratos.component.html',
  styleUrls: ['./contratos.component.scss']
})
export class ContratosComponent {

  constructor(private router: Router) { }

  openModal() {
    this.router.navigate(['/admin/contratosdetails']);
  }
}