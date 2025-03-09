import { Component } from '@angular/core';
import { SideBarAdminComponent } from '../side-bar-admin/side-bar-admin.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-principal-admin',
  standalone: true,
  imports: [SideBarAdminComponent, RouterOutlet],
  templateUrl: './principal-admin.component.html',
  styleUrl: './principal-admin.component.scss'
})
export class PrincipalAdminComponent {

}
