import { Component, Input } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { jwtDecode } from 'jwt-decode';
import { PaymentsService } from '../../../services/payments.service';
import { PagamentoDTO } from '../../../../dto/PagamentoDTO';
import { CommonModule } from '@angular/common';
import { NotificationsService } from '../../../services/notifications.service';
import { AnuncioDTO } from '../../../../dto/AnuncioDTO';

interface CustomJwtPayload {
  name: string;
}

@Component({
  imports: [CommonModule],
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.scss'] // Aqui estava o erro
})
export class DashboardUserComponent {

  userName: string = 'User';
  nextPayment: string = '0.00';
  lastPayments: PagamentoDTO[] = [];
  lastNotifications: AnuncioDTO[] = [];

  getCookie(): string {
    return this.cookieService.get('acs');
}


  constructor(private cookieService: CookieService, private paymentService: PaymentsService, private notificationService: NotificationsService) { 
    this.loadUserName();
    this.loadNextPayment();
    this.loadLastPayments();
    this.loadLastNotifications();
  }

  loadUserName() {
    const token = this.getCookie();
    const decoded = jwtDecode<CustomJwtPayload>(token);
    this.userName = decoded.name;
  }

  loadNextPayment() {
    this.paymentService.proximoPGTO().subscribe((payment) => {
      this.nextPayment = payment.valor.toFixed(2).toString();
    });
  }

  loadLastPayments() {
    this.paymentService.next6PGTO().subscribe((payments) => {
      this.lastPayments = payments;
    });
  }

  loadLastNotifications() {
    this.notificationService.getNotifications().subscribe((notifications) => {
      this.lastNotifications = notifications;
    });
  }




  
}