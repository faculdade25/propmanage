import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common'; // Para ngClass e diretivas
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar-menu-lateral',
  standalone: true,
  imports: [CommonModule], // Necessário para ngClass
  templateUrl: './sidebar-menu-lateral.component.html',
  styleUrls: ['./sidebar-menu-lateral.component.scss']
})
export class SidebarMenuLateralComponent {
  @Input() isVisible: boolean = false; // Recebe do UserComponent
  @Output() toggleEvent = new EventEmitter<void>(); // Emite para o UserComponent

  constructor(private router: Router) {}

  navigateTo(route: string): void {
    this.router.navigate([route]);
    // Colapsa o sidebar apenas em mobile (< 768px)
    if (window.innerWidth < 768) {
      this.toggleEvent.emit(); // Emite evento para colapsar o sidebar em mobile
    }
  }
}