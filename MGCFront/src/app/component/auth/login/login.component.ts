import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {LoginRequest} from "../../../services/interfaces/loginRequest";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginError:String='';
  userLoginOn:boolean=false;

  loginForm = this.formBuilder.group({
    username: ['Admin', [Validators.required]],
    password: ['admin', [Validators.required]]
  })


  constructor(private formBuilder: FormBuilder, private router:Router, private auth:AuthService) {
  }

  get username() { return this.loginForm.controls.username; }
  get password() { return this.loginForm.controls.password; }


  login(){
    if (this.loginForm.valid){
      this.auth.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          console.log(userData)
          },
        error: (error) => {
          console.log(error)
          this.loginError=error
          },
        complete: () => {
          console.log("Login completado")
          this.router.navigateByUrl('index')
          this.loginForm.reset()
          this.userLoginOn=true
        }
    })
    }
    else {

      this.loginForm.markAllAsTouched();
      alert("error al ingresar los datos")
    }
  }

}
