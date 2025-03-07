import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { UserComponent } from './components/user/user.component';
import { DashboardUserComponent } from './components/user/dashboard-user/dashboard-user.component';
import { MyContractsComponent } from './components/user/my-contracts/my-contracts.component';
import { NotificationsComponent } from './components/user/notifications/notifications.component';
import { PaymentsComponent } from './components/user/payments/payments.component'; 
import { SecurityContext } from '@angular/core';

import { NgModule } from '@angular/core';
import { SettingsComponent } from './components/user/settings/settings.component';


export const routes: Routes = [
    { path: "", redirectTo: "login", pathMatch: "full" },
    {path: "login", component: LoginComponent},

    {
      path: 'user',
      component: UserComponent,
      children: [
        { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
        { path: 'dashboard', component: DashboardUserComponent },
        { path: 'my-contracts', component: MyContractsComponent },
        { path: 'notifications', component: NotificationsComponent },
        { path: 'payments', component: PaymentsComponent },
        { path: 'settings', component: SettingsComponent }
      ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
export class AppRoutingModule { }