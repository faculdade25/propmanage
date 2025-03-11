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

import { PrincipalAdminComponent } from './components/layot/principal-admin/principal-admin.component';
import { DashboardComponent } from './components/layot/dashboard/dashboard.component';
import { ApartamentosComponent } from './components/layot/apartamentos/apartamentos.component';
import { ContratosComponent } from './components/layot/contratos/contratos.component';
import { PagamentosComponent } from './components/layot/pagamentos/pagamentos.component';
import { UsuariosComponent } from './components/layot/usuarios/usuarios.component';
import { NotificacoesComponent } from './components/layot/notificacoes/notificacoes.component';
import { ConfiguracoesComponent } from './components/layot/configuracoes/configuracoes.component';
import { ContratosdetailsComponent } from './components/layot/contratos/contratosdetails/contratosdetails.component';

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
    },
    {path: "admin", component: PrincipalAdminComponent, children:[
      {path: "dashboard", component: DashboardComponent},
      {path: "apartamentos", component: ApartamentosComponent},
      {path: "contratos", component: ContratosComponent},
      {path: "contratosdetails", component: ContratosdetailsComponent},
      {path: "pagamentos", component: PagamentosComponent},
      {path: "usuarios", component: UsuariosComponent},
      {path: "notificacoes", component: NotificacoesComponent},
      {path: "configuracoes", component: ConfiguracoesComponent}
 ]}
];

