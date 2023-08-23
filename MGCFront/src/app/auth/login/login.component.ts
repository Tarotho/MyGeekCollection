import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm = this.formBuilder.group({
    username: ['Patata', [Validators.required]],
    password: ['', [Validators.required]]
  })


  constructor(private formBuilder: FormBuilder) {
  }


  login(){
    if (this.loginForm.valid){
      console.log("Llamar al servicio login")
    }
    else {
      console.log("error al ingresar los datos")
    }
  }

}
