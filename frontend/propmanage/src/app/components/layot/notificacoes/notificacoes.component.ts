import { Component } from '@angular/core';
import { NotificacoesdetailsComponent } from './notificacoesdetails/notificacoesdetails.component';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog'; // <-- Nova importação
import { NotificationsService } from '../../../services/notifications.service';
import { AnuncioDTO } from '../../../../dto/AnuncioDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-notificacoes',
  standalone: true,
  imports: [ MatDialogModule, CommonModule],
  templateUrl: './notificacoes.component.html',
  styleUrl: './notificacoes.component.scss'
})
export class NotificacoesComponent {
  lastNotifications: AnuncioDTO[] = [];
  constructor(private dialog: MatDialog, private notificationService: NotificationsService) {
    this.loadLastNotifications();
  }


  openModal() {
    this.dialog.open(NotificacoesdetailsComponent, { // <-- Nova sintaxe
      panelClass: ['max-w-2xl', 'mx-auto'], // Estilo Tailwind
      width: '90%', // Largura personalizada
      height: 'auto'
    });
    this.lastNotifications = [];
    this.loadLastNotifications();
  }

  loadLastNotifications() {
    this.notificationService.getNotifications().subscribe((notifications) => {
      this.lastNotifications = notifications;
    });
  }
}