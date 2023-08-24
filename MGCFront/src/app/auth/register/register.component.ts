import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {RegisterRequest} from "../../services/interfaces/registerRequest";
import {AuthService} from "../../services/auth.service";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerError:String='';
  userLoginOn:boolean=false;

  registerForm = this.formBuilder.group({
    username: ['Admin', [Validators.required]],
    name: ['admin', [Validators.required]],
    email: ['a@a.es', [Validators.required, Validators.email]],
    password: ['admin', [Validators.required]],
    password2: ['admin', [Validators.required]],
  })

  constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService) {
  }

  register() {
    if (this.registerForm.valid) {
      if (this.registerForm.get('password')?.value == this.registerForm.get('password2')?.value) {
        const newUser = {
          username: this.registerForm.get('username')?.value,
          name: this.registerForm.get('name')?.value,
          email: this.registerForm.get('email')?.value,
          password: this.registerForm.get('password')?.value
        }
        this.auth.register(newUser as RegisterRequest).subscribe({
          next: (token) => {
            console.log(token)
          },
          error: (error) => {
            console.log(error)
            this.registerError=error
          },
          complete: () => {
            console.log("Registro completado")
            this.router.navigateByUrl('index')
            this.registerForm.reset()
            this.userLoginOn=true
          }
        })

      } else {
        this.registerForm.markAllAsTouched();
        alert("las contraseñas no son iguales")
      }
    }else {
      alert("los datos no son correctos")
    }
  }


}
