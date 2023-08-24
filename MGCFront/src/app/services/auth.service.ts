import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {RegisterRequest} from "./interfaces/registerRequest";
import {LoginRequest} from "./interfaces/loginRequest";
import {catchError, Observable, throwError, BehaviorSubject, tap} from "rxjs";
import {User} from "./interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  currentUserLoginOn: BehaviorSubject<boolean>= new BehaviorSubject<boolean>(false)
  currentUserData: BehaviorSubject<User>=new BehaviorSubject<User>({token:'', username:''})

  register(newUser: RegisterRequest): Observable<User> {
    console.log(newUser as RegisterRequest)
    return this.http.post<User>('http://localhost:8081/auth/register', newUser as RegisterRequest).pipe(
        tap(userData =>{
            this.currentUserData.next(userData)
            this.currentUserLoginOn.next(true)
        }),
      catchError(this.handleError)
    )
  }

  login(credentials: LoginRequest): Observable<User> {
    return this.http.post<User>('http://localhost:8081/auth/login', credentials as LoginRequest).pipe(
      tap(userData =>{
        this.currentUserData.next(userData)
        this.currentUserLoginOn.next(true)
      }),
      catchError(this.handleError)
    )
  }

  private handleError(error:HttpErrorResponse){
    if (error.status===0){
      console.error('se produjo un error', error.error)
    }else {
      console.error('Backenmd encontro el código de estado', error.status, error.error)
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'))
  }

  get userData():Observable<User>{
    return this.currentUserData.asObservable()
  }
  get userLogin():Observable<boolean>{
    return this.currentUserLoginOn.asObservable()
  }

}
