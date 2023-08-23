import {Component, OnInit} from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm=this.formBuilder.group({
    username:['Don Patata', [Validators.required]],
    name:['Patata', [Validators.required]],
    email:['example@example.com', [Validators.required, Validators.email]],
    password:['', [Validators.required]],
    password2:['', [Validators.required]]
  })


  constructor(private formBuilder:FormBuilder) {
  }


}
