import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { FooterNavbarComponent } from './shared/navbar/footer-navbar/footer-navbar.component';
import { HeaderNavbarComponent } from './shared/navbar/header-navbar/header-navbar.component';
import { SideNavbarComponent } from './shared/navbar/side-navbar/side-navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    FooterNavbarComponent,
    HeaderNavbarComponent,
    SideNavbarComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
