import { Component } from '@angular/core';
import { PagamentoDTO } from '../../../../dto/PagamentoDTO';
import { AnuncioDTO } from '../../../../dto/AnuncioDTO';
import { NotificationsService } from '../../../services/notifications.service';
import { CommonModule } from '@angular/common';
import { jwtDecode } from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import { PaymentsService } from '../../../services/payments.service';

interface CustomJwtPayload {
  name: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  pagamentos: PagamentoDTO[] = [];

  userName: string = 'User';
  saldoAtual: string = "0.00";
  saldoFuturo: string = "0.00";
  tempSaldo: number = 0;

  lastNotifications: AnuncioDTO[] = [];


  getCookie(): string {
    return this.cookieService.get('acs');
  }
  
    constructor(private notificationService: NotificationsService, private cookieService: CookieService, private paymentService: PaymentsService) {
      this.loadSaldoAtual();
      this.loadLastNotifications();
      this.loadUserName();
      this.loadSaldoFuturo();
      this.loadPagamentos();
    }

    loadUserName() {
        const token = this.getCookie();
        const decoded = jwtDecode<CustomJwtPayload>(token);
        this.userName = decoded.name;
      }
    
    
    loadLastNotifications() {
      this.notificationService.getNotifications().subscribe((notifications) => {
        this.lastNotifications = notifications;
      });
    }

    loadSaldoAtual(){
      this.paymentService.totalSaldo().subscribe((saldo) => {
        console.log(saldo);
        this.tempSaldo = saldo;
        console.log(this.tempSaldo);
        this.saldoAtual = saldo.toFixed(2).toString();
      });
    }

    loadSaldoFuturo() {
      this.paymentService.totalSaldoPendente().subscribe((saldo) => {
        console.log(saldo);
        this.tempSaldo += saldo; 
        console.log(this.tempSaldo);
        this.saldoFuturo = this.tempSaldo.toFixed(2).toString();
      });
    }

    loadPagamentos() {
      this.paymentService.allPagamentosPendente().subscribe((pagamentos) => {
        this.pagamentos = pagamentos;
      });
    }



}
