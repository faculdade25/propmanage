import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ContractsService } from '../../../services/contracts.service';
import { ContratoDTOuser } from '../../../../dto/ContratoDTO';


@Component({
  imports: [CommonModule],
  selector: 'app-my-contracts',
  templateUrl: './my-contracts.component.html',
  styleUrl: './my-contracts.component.scss'
})

export class MyContractsComponent implements OnInit {
  allContracts: ContratoDTOuser[] = [];
  processo: string = '';
  contratoAtual!: ContratoDTOuser;
  loading: boolean = true;
  error: string | null = null;

  constructor(private contractService: ContractsService) {}

  ngOnInit(): void {
    this.loadContracts();
    this.loadContratoAtual();
  }

  loadContracts() {
    this.loading = true;
    this.contractService.allContratos().subscribe({
      next: (contracts) => {
        this.allContracts = contracts;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erro ao carregar contratos:', err);
        this.error = 'Não foi possível carregar os contratos.';
        this.loading = false;
      }
    });
  }

  loadContratoAtual() {
    this.loading = true;
    this.contractService.contratoAtual().subscribe({
      next: (contract) => {
        this.contratoAtual = contract;
        this.processo = contract.processo || '';
        this.loading = false;
        console.log('Contrato atual:', contract);
      },
      error: (err) => {
        console.error('Erro ao carregar contrato atual:', err);
        this.error = 'Não foi possível carregar o contrato atual.';
        this.loading = false;
      }
    });
  }
}