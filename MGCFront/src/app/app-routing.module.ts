import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./component/pages/dashboard/dashboard.component";
import {LoginComponent} from "./component/auth/login/login.component";
import {RegisterComponent} from "./component/auth/register/register.component";
import {UserProfileComponent} from "./component/pages/user/user-profile/user-profile.component";
import {UsuariosComponent} from "./component/usuarios/usuarios.component";
import {UserListComponent} from "./component/pages/user/user-list/user-list.component";
import {UserCollectionComponent} from "./component/pages/user/user-collection/user-collection.component";

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'index', component: UsuariosComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: UserProfileComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'users', component: UserListComponent},
  {path: 'user/:id', component: UserCollectionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
