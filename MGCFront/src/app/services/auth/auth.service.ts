import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {RegisterRequest} from "./interfaces/registerRequest";
import {LoginRequest} from "./interfaces/loginRequest";
import {BehaviorSubject, catchError, map, Observable, tap, throwError} from "rxjs";
import {environment} from "../../../environments/environment.development";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)
    currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("")

    constructor(private http: HttpClient) {
        this.currentUserLoginOn = new BehaviorSubject<boolean>(sessionStorage.getItem("token") != null);
        this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem("token") || "");
    }

    login(credentials: LoginRequest): Observable<any> {
        return this.http.post<any>(`${environment.urlAuth}login`, credentials as LoginRequest).pipe(
            tap((userData) => {
                sessionStorage.setItem("token", userData.token)
                this.currentUserData.next(userData.token);
                this.currentUserLoginOn.next(true);
            }),
            map((userData) => userData.token),
            catchError(this.handleError)
        )
    }

    logOut(): void {
        sessionStorage.removeItem("token");
        this.currentUserLoginOn.next(false);
    }

    register(credentials: RegisterRequest): Observable<any> {
        return this.http.post<any>(`${environment.urlAuth}register`, credentials as RegisterRequest).pipe(
            tap((userData) => {
                sessionStorage.setItem("token", userData.token)
                this.currentUserData.next(userData.token);
                this.currentUserLoginOn.next(true);
            }),
            map((userData) => userData.token),
            catchError(this.handleError)
        )
    }


    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
            console.error('se produjo un error', error.error)
        } else {
            console.error('Backenmd encontro el código de estado', error)
        }
        return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'))
    }

    get getToken():String{
        return this.currentUserData.value
    }
}
