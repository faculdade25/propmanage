import { Component } from '@angular/core';
import {
  HlmCaptionComponent,
  HlmTableComponent,
  HlmTdComponent,
  HlmThComponent,
  HlmTrowComponent,
} from '@spartan-ng/ui-table-helm';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [HlmTableComponent, HlmTrowComponent, HlmThComponent, HlmTdComponent, HlmCaptionComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  userName: string = 'User';

}
