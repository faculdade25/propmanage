import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ApartamentosdetailsComponent } from './apartamentosdetails/apartamentosdetails.component';
import { ApartamentosService } from '../../../services/apartamentos.service';
import { CommonModule } from '@angular/common';
import { ApartamentoDTO } from '../../../../dto/ApartamentoDTO';

@Component({
  selector: 'app-apartamentos',
  standalone: true,
  imports: [MatDialogModule, CommonModule],
  templateUrl: './apartamentos.component.html',
  styleUrl: './apartamentos.component.scss'
})
export class ApartamentosComponent {

  apartamentos: ApartamentoDTO[] = [];

  tempApartamento!: ApartamentoDTO;

  constructor(private dialog: MatDialog, private apartamentoService: ApartamentosService) {
    this.loadApartamentos();
  }

  loadApartamentos(){
    this.apartamentoService.allApartamentos().subscribe((apartamentos) => {
      this.apartamentos = apartamentos;
    });
  }

  openModal(apartamento: ApartamentoDTO) {
    this.dialog.open(ApartamentosdetailsComponent, {
      data: apartamento,
      panelClass: ['modal'], 
      width: '450px',
      height: '450px'
    });
  }
}