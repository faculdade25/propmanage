import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider'; 
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/select';




interface Tenant {
  id: number;
  nome: string;
  email: string;
  telefone: string;
}

interface NewTenant {
  nome: string;
  sobrenome: string;
  email: string;
  telefone: string;
  cpf: string;
  rg: string;
  profissao: string;
  nascimento: string;
}

// Componente atualizado
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ContractsService } from '../../../../services/contracts.service';
import { ContratosService } from '../../../../services/contratos.service';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule, ReactiveFormsModule,FormsModule, MatFormFieldModule,  MatDividerModule, MatButtonModule, MatInputModule, MatSelect, MatOption],
  selector: 'app-contratosdetails',
  templateUrl: './contratosdetails.component.html',
  styleUrls: ['./contratosdetails.component.scss']
})
export class ContratosdetailsComponent implements OnInit {
  contractForm: FormGroup;
  newTenantForm: FormGroup;
  tenants: Tenant[] = [];
  loading = false;
  activeTab: 'existente' | 'novo' = 'existente';

  constructor(
    private fb: FormBuilder,
    private contratosService: ContratosService
  ) {
    this.contractForm = this.fb.group({
      titularId: this.fb.control<number | null>(null, Validators.required),
      apnum: this.fb.nonNullable.control(1, [Validators.required, Validators.min(1)]),
      valorCondominio: this.fb.nonNullable.control(500.00, Validators.required),
      valorIptu: this.fb.nonNullable.control(150.00, Validators.required),
      valorInternet: this.fb.nonNullable.control(100.00, Validators.required),
      valorAluguel: this.fb.nonNullable.control(2000.00, Validators.required),
      entrada: this.fb.nonNullable.control('', Validators.required),
      processo: this.fb.nonNullable.control('', Validators.required),
      referente: this.fb.nonNullable.control(2025, Validators.required),
      dataAceite: this.fb.nonNullable.control('', Validators.required),
      status: this.fb.nonNullable.control('DEFERIDO', Validators.required)
    });

    this.newTenantForm = this.fb.group({
      nome: this.fb.nonNullable.control('', Validators.required),
      sobrenome: this.fb.nonNullable.control('', Validators.required),
      email: this.fb.nonNullable.control('', [Validators.required, Validators.email]),
      telefone: this.fb.nonNullable.control('', Validators.required),
      cpf: this.fb.nonNullable.control('', Validators.required),
      rg: this.fb.nonNullable.control('', Validators.required),
      profissao: this.fb.nonNullable.control('', Validators.required),
      nascimento: this.fb.nonNullable.control('', Validators.required)
    });
  }

  ngOnInit() {
    this.loadTenants();
  }

  loadTenants() {
    this.loading = true;
    this.contratosService.getTenants().subscribe({
      next: (tenants) => this.tenants = tenants,
      error: (error) => console.error('Erro ao carregar inquilinos:', error),
      complete: () => this.loading = false
    });
  }

  createTenant() {
    if (this.newTenantForm.invalid) return;
    
    this.loading = true;
    this.contratosService.createTenant(this.newTenantForm.value).subscribe({
      next: (newTenant) => {
        this.tenants.push(newTenant);
        this.contractForm.patchValue({ titularId: newTenant.id });
        this.activeTab = 'existente';
      },
      error: (error) => console.error('Erro ao criar inquilino:', error),
      complete: () => this.loading = false
    });
  }

  submitContract() {
    if (this.contractForm.invalid) return;
    
    const rawValues = this.contractForm.getRawValue();
    const payload = {
      ...rawValues,
      titularId: rawValues.titularId as number,
      entrada: new Date(rawValues.entrada).toISOString(),
      dataAceite: new Date(rawValues.dataAceite).toISOString()
    };

    if (typeof payload.titularId !== 'number' || isNaN(new Date(payload.entrada).getTime())) {
      console.error('Valores inválidos no formulário');
      return;
    }

    console.log('Dados do contrato:', payload);
  }
}
