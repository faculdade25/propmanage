import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { PrincipalAdminComponent } from './components/layot/principal-admin/principal-admin.component';
import { DashboardComponent } from './components/layot/dashboard/dashboard.component';
import { ApartamentosComponent } from './components/layot/apartamentos/apartamentos.component';
import { ContratosComponent } from './components/layot/contratos/contratos.component';
import { Contratosdetails2Component } from './components/layot/contratos/contratosdetails2/contratosdetails2.component';
import { PagamentosComponent } from './components/layot/pagamentos/pagamentos.component';
import { UsuariosComponent } from './components/layot/usuarios/usuarios.component';
import { NotificacoesComponent } from './components/layot/notificacoes/notificacoes.component';
import { ConfiguracoesComponent } from './components/layot/configuracoes/configuracoes.component';

export const routes: Routes = [
    { path: "", redirectTo: "login", pathMatch: "full" },
    {path: "login", component: LoginComponent},
    {path: "admin", component: PrincipalAdminComponent, children:[
         {path: "dashboard-admin", component: DashboardComponent},
         {path: "apartamentos", component: ApartamentosComponent},
         {path: "contratos", component: ContratosComponent},
         {path: "contratos2", component: Contratosdetails2Component},
         {path: "pagamentos", component: PagamentosComponent},
         {path: "usuarios", component: UsuariosComponent},
         {path: "notificacoes", component: NotificacoesComponent},
         {path: "configuracoes", component: ConfiguracoesComponent},






    ]}
];

