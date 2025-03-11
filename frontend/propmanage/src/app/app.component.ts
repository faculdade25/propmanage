import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SideBarAdminComponent } from './components/layot/side-bar-admin/side-bar-admin.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'propmanage';
}
