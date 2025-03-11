import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar-admin',
  standalone: true,
  imports: [],
  templateUrl: './side-bar-admin.component.html',
  styleUrl: './side-bar-admin.component.scss'
})
export class SideBarAdminComponent {

  currentRoute!: string;

  constructor(private router: Router) {}

  navigateTo(route: string): void {
    this.currentRoute = route;
    this.router.navigate([route]);
  }

}
