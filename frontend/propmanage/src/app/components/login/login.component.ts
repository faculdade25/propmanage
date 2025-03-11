import { Component } from '@angular/core';
import { LoginMenuComponent } from '../../block/login-menu';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [LoginMenuComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
