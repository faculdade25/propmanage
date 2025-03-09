import { Component } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { ApartamentosdetailsComponent } from './apartamentosdetails/apartamentosdetails.component';


@Component({
  selector: 'app-apartamentos',
  standalone: true,
  imports: [ MdbModalModule,],
  templateUrl: './apartamentos.component.html',
  styleUrl: './apartamentos.component.scss'
})
export class ApartamentosComponent {

  modalRef: MdbModalRef<ApartamentosdetailsComponent> | null = null;

    constructor(private modalService: MdbModalService) {}

    openModal() {
      this.modalRef = this.modalService.open(ApartamentosdetailsComponent, {
        modalClass: 'modal-dialog-centered'
      })
    }

}




