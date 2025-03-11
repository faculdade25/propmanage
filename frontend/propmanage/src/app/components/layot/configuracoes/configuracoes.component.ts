import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-configuracoes',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './configuracoes.component.html',
  styleUrl: './configuracoes.component.scss'
})
export class ConfiguracoesComponent {

}
