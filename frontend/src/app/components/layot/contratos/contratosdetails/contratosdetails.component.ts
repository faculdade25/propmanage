import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDividerModule } from '@angular/material/divider'; 
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelect } from '@angular/material/select';
import { MatOption } from '@angular/material/select';
import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { InquilinoDTO } from '../../../../../dto/requests/InquilinoMinDTO';
import { environment } from '../../../../../../enviroments/enviroment';
import { ContratoDTOFull, StatusContrato } from '../../../../../dto/ContratoDTOFull';
import { ApartamentoDTO } from '../../../../../dto/ApartamentoDTO';
import { Router } from '@angular/router';


@Component({
  imports: [CommonModule, FormsModule, MatFormFieldModule,  MatDividerModule, MatButtonModule, MatInputModule, MatSelect, MatOption, NgFor],
  selector: 'app-contratosdetails',
  templateUrl: './contratosdetails.component.html',
  styleUrls: ['./contratosdetails.component.scss']
})
export class ContratosdetailsComponent {

  inquilinos: InquilinoDTO[] = [];
  apartamentos: ApartamentoDTO[] = [];
  contrato!: ContratoDTOFull;
  selectedInquilino: number = 0;
  statusSelecionado: string = "";
  internetPrice: number = 0;
  apnum: number = 0;
  iptuPrice: number = 0;
  condominioPrice: number = 0;
  aluguelPrice: number = 0;
  entrada: Date = new Date();
  processo: string = "ALUGUEL";
  referente: number = new Date().getFullYear();
  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private router: Router) {
    this.getInquilinos();
    this.getApartamentos();
    console.log(this.inquilinos);
  }

  getInquilinos(){
    this.http.get<InquilinoDTO[]>(this.apiUrl + "/predios/inquilinos").subscribe(
      (data) => {
        this.inquilinos = data;
        console.log(this.inquilinos);
        console.log(data);
      }
    );
  }

  getApartamentos() {
    this.http.get<ApartamentoDTO[]>(this.apiUrl + "/predios/apartamentos").subscribe(
      (data) => {
        this.apartamentos = data.filter(apartamento => apartamento.status === "VAGO");
      }
    );
  }

  setUpNewContract() {
    this.http.post(`${this.apiUrl}/api/contratos/create`, {
      titularId: this.selectedInquilino,
      apnum: this.apnum,
      valorCondominio: parseFloat(this.condominioPrice.toString()),
      valorIptu: parseFloat(this.iptuPrice.toString()),
      valorInternet: parseFloat(this.internetPrice.toString()),
      valorAluguel: parseFloat(this.aluguelPrice.toString()),
      entrada: new Date(this.entrada).toISOString().split('T')[0], // Formata entrada para "YYYY-MM-DD"
      processo: this.processo,
      referente: this.referente,
      dataAceite: new Date().toISOString().split('T')[0], // Formata dataAceite para "YYYY-MM-DD"
      status: StatusContrato[this.statusSelecionado as keyof typeof StatusContrato]
    }).subscribe({
      next: (response) => {
        console.log("Contrato cadastrado com sucesso!", response);
        this.router.navigate(['/admin/contratos']);
      },
      error: (error) => {
        console.error("Erro ao cadastrar contrato:", error);
      }
    });
  }
  
}
