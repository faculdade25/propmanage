import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagamentoDTO } from '../../../../dto/PagamentoDTO';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../enviroments/enviroment';

@Component({
  selector: 'app-pagamentos',
  standalone: true,
  imports: [ CommonModule],
  templateUrl: './pagamentos.component.html',
  styleUrl: './pagamentos.component.scss'
})
export class PagamentosComponent {

  pagamentos: PagamentoDTO[] = [];
  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
    this.loadPagamentos();
   }

   loadPagamentos(){
      this.http.get<PagamentoDTO[]>(this.apiUrl + "/payments/predio/todos").subscribe(
        (data) => {
          this.pagamentos = data;
          console.log(this.pagamentos)
        }
      );
   }

}
