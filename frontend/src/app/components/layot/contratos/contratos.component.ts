import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ContratosdetailsComponent } from './contratosdetails/contratosdetails.component';
import { CommonModule } from '@angular/common';
import { Route, Router } from '@angular/router';
import { ContratoDTOFull } from '../../../../dto/ContratoDTOFull';
import { ContratosService } from '../../../services/contratos.service';
import { ContractsService } from '../../../services/contracts.service';

@Component({
  selector: 'app-contratos',
  standalone: true,
  imports: [MatDialogModule, CommonModule],
  templateUrl: './contratos.component.html',
  styleUrls: ['./contratos.component.scss']
})
export class ContratosComponent {

  contratos: ContratoDTOFull[] = [];

  constructor(private router: Router, private contratoService: ContractsService) { 
    this.loadContratos();
  }

  openModal() {
    this.router.navigate(['/admin/contratosdetails']);
  }

  loadContratos(){
    this.contratoService.allContratosAdmin().subscribe(
      (data) => {
        this.contratos = data;
      }
    );
  }
}