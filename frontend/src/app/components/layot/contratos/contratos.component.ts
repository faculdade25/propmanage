import { Component } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { ContratosdetailsComponent } from './contratosdetails/contratosdetails.component';

@Component({
  selector: 'app-contratos',
  standalone: true,
  imports: [ MdbModalModule,],
  templateUrl: './contratos.component.html',
  styleUrl: './contratos.component.scss'
})
export class ContratosComponent {

  modalRef: MdbModalRef<ContratosdetailsComponent> | null = null;
  
      constructor(private modalService: MdbModalService) {}
  
      openModal() {
        this.modalRef = this.modalService.open(ContratosdetailsComponent, {
          modalClass: 'modal-dialog-centered'
        })
      }

}
