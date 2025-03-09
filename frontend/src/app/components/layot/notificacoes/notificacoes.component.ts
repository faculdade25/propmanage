import { Component } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { NotificacoesdetailsComponent } from './notificacoesdetails/notificacoesdetails.component';

@Component({
  selector: 'app-notificacoes',
  standalone: true,
  imports: [ MdbModalModule],
  templateUrl: './notificacoes.component.html',
  styleUrl: './notificacoes.component.scss'
})
export class NotificacoesComponent {

   modalRef: MdbModalRef<NotificacoesdetailsComponent> | null = null;
    
        constructor(private modalService: MdbModalService) {}
    
        openModal() {
          this.modalRef = this.modalService.open(NotificacoesdetailsComponent, {
            modalClass: 'modal-dialog-centered'
          })
        }

}
