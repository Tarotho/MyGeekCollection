import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderNavbarComponent } from './component/shared/navbar/header-navbar/header-navbar.component';
import { FooterNavbarComponent } from './component/shared/navbar/footer-navbar/footer-navbar.component';
import { LoginComponent } from './component/auth/login/login.component';
import { RegisterComponent } from './component/auth/register/register.component';
import { DashboardComponent } from './component/pages/dashboard/dashboard.component';
import { AppRoutingModule } from './app-routing.module';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { UserProfileComponent } from './component/pages/user/user-profile/user-profile.component';
import { UsuariosComponent } from './component/usuarios/usuarios.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderNavbarComponent,
    FooterNavbarComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    UserProfileComponent,
    UsuariosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
