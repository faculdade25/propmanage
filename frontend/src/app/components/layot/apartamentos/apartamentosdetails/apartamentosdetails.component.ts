import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';


@Component({
  selector: 'app-apartamentosdetails',
  standalone: true,
  imports: [    MdbFormsModule,  ],
  templateUrl: './apartamentosdetails.component.html',
  styleUrl: './apartamentosdetails.component.scss'
})
export class ApartamentosdetailsComponent {
  constructor(public modalRef: MdbModalRef<ApartamentosdetailsComponent>) {}

}





