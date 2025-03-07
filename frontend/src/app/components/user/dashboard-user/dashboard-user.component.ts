import { Component,  Input } from '@angular/core';

@Component({
  selector: 'app-dashboard-user',

  templateUrl: './dashboard-user.component.html',
  styleUrl: './dashboard-user.component.scss'
})
export class DashboardUserComponent {
  @Input() userName: string = 'Ed';
}



