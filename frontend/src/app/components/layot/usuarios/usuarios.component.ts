import { Component } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { UsuariosdetailsComponent } from './usuariosdetails/usuariosdetails.component';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [ MdbModalModule],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.scss'
})
export class UsuariosComponent {

  modalRef: MdbModalRef<UsuariosdetailsComponent> | null = null;
    
        constructor(private modalService: MdbModalService) {}
    
        openModal() {
          this.modalRef = this.modalService.open(UsuariosdetailsComponent, {
            modalClass: 'modal-dialog-centered'
          })
        }

}
