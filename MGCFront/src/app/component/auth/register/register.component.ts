import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {RegisterRequest} from "../../../services/auth/interfaces/registerRequest";
import {AuthService} from "../../../services/auth/auth.service";


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {

    registerError: String = '';
    userLoginOn: boolean = false;

    registerForm = this.formBuilder.group({
        username: ['', [Validators.required]],
        email: ['', [Validators.required]],
        password: ['', [Validators.required]],
        password2: ['', [Validators.required]]
    })

    constructor(private formBuilder: FormBuilder, private router: Router, private auth: AuthService) {
    }

    get username() {
        return this.registerForm.controls.username;
    }

    get email() {
        return this.registerForm.controls.email;
    }

    get password() {
        return this.registerForm.controls.password;
    }

    get password2() {
        return this.registerForm.controls.password2;
    }


    register() {
        if (this.registerForm.valid) {
            if (this.password2.value === this.password.value) {
                this.auth.register(this.registerForm.value as RegisterRequest).subscribe({
                    next: (userData) => {
                        console.log(userData)
                    },
                    error: (error) => {
                        console.log(error)
                        this.registerError = error
                    },
                    complete: () => {
                        console.log("Login completado")
                        this.router.navigateByUrl('')
                        this.registerForm.reset()
                        this.userLoginOn = true
                    }
                })
            } else {
                console.log("Las contraseñas no coinciden.")
            }
        } else {
            console.log("El formulario tiene datos invalidos.");
        }
    }


}
