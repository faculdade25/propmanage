import { Component } from '@angular/core';
import { AnuncioDTO } from '../../../../dto/AnuncioDTO';
import { CommonModule } from '@angular/common';
import { NotificationsService } from '../../../services/notifications.service';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.scss'
})
export class NotificationsComponent {

  lastNotifications: AnuncioDTO[] = [];

  constructor(private notificationService: NotificationsService) {
    this.loadLastNotifications();
  }
  
  loadLastNotifications() {
    this.notificationService.getNotifications().subscribe((notifications) => {
      this.lastNotifications = notifications;
    });
  }

}
