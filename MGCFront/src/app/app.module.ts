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
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { UserProfileComponent } from './component/pages/user/user-profile/user-profile.component';
import { UsuariosComponent } from './component/usuarios/usuarios.component';
import {JwtInterceptorInterceptor} from "./services/auth/jwt-interceptor.interceptor";
import {ErrorInterceptorInterceptor} from "./services/auth/error-interceptor.interceptor";
import { UserCollectionComponent } from './component/pages/user/user-collection/user-collection.component';
import { UserListComponent } from './component/pages/user/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderNavbarComponent,
    FooterNavbarComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    UserProfileComponent,
    UsuariosComponent,
    UserCollectionComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
      {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptorInterceptor,multi:true},
      {provide:HTTP_INTERCEPTORS,useClass:ErrorInterceptorInterceptor,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
