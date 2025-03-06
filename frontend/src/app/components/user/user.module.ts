// src/app/components/user/user.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DashboardUserComponent } from './dashboard-user/dashboard-user.component';
import { MyContractsComponent } from './my-contracts/my-contracts.component';
import { PaymentsComponent } from './payments/payments.component';
import { SettingsComponent } from './settings/settings.component';

const routes = [
  { 
    path: '', 
    loadComponent: () => import('./user.component').then(m => m.UserComponent), // Lazy-load standalone UserComponent
    children: [
      { path: 'dashboard', component: DashboardUserComponent },
      { path: 'payments', component: PaymentsComponent },
      { path: 'my-contracts', component: MyContractsComponent },
      { path: 'notifications', loadComponent: () => import('./notifications/notifications.component').then(m => m.NotificationsComponent) },
      { path: 'settings', component: SettingsComponent }
    ]
  }
];

@NgModule({
  declarations: [
    DashboardUserComponent,
    MyContractsComponent,
    PaymentsComponent,
    SettingsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: []
})
export class UserModule { }