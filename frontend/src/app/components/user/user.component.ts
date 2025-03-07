import { Component, HostListener } from '@angular/core';
import { SidebarMenuLateralComponent } from './sidebar-menu-lateral/sidebar-menu-lateral.component';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, RouterOutlet, SidebarMenuLateralComponent],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {
  isSidebarVisible = false; // Começa sempre escondido, mesmo em desktop, para garantir controle manual

  toggleSidebar(): void {
    console.log('Toggling sidebar, new state:', !this.isSidebarVisible); // Log para depuração
    this.isSidebarVisible = !this.isSidebarVisible; // Alterna o estado apenas pelo toggle do header
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event | null): void {
    const width = window.innerWidth;
    console.log('Window resized to:', width, 'Setting isSidebarVisible to:', width >= 768); // Log para depuração
    if (width >= 768) {
      this.isSidebarVisible = true; // Torna o sidebar visível em desktop
    } else {
      this.isSidebarVisible = false; // Garante que o sidebar fique escondido em mobile
    }
  }

  ngOnInit(): void {
    // Define o estado inicial baseado no tamanho da tela ao carregar
    this.onResize(null);
  }
}