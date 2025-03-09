import { Component } from '@angular/core';
import { PaymentsService } from '../../../services/payments.service';
import { PagamentoDTO } from '../../../../dto/PagamentoDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payments',
  imports: [CommonModule],
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.scss'
})
export class PaymentsComponent {
  nextPayment: string = '0.00';
  nextVencimento: string = '00/00/0000';
  allPayments: PagamentoDTO[] = [];

  constructor(private paymentService: PaymentsService) {
    this.loadNextPayment();
    this.loadAllPayments();
   }

  loadNextPayment() {
    this.paymentService.proximoPGTO().subscribe((payment) => {
      this.nextPayment = payment.valor.toFixed(2).toString();
      this.nextVencimento = payment.vencimento.toString();
      const parts = payment.vencimento.toString().split('-');
      this.nextVencimento = `${parts[2]}/${parts[1]}/${parts[0]}`;
    });
  }

  loadAllPayments() {
    this.paymentService.allPGTO().subscribe((payments) => {
      this.allPayments = payments;
    });

  }

}
