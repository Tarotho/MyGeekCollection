import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "./interfaces/user";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient : HttpClient) { }

  getAllUsers():Observable<User[]>{
    return this.httpClient.get<User[]>(`${environment.urlApi}user`)
  }

  getUserById(id:number):Observable<User>{
    return this.httpClient.get<User>(`${environment.urlApi}user/${id}`).pipe(
      catchError(this.handleError)
    )
  }
  getProfile():Observable<User>{
    return this.httpClient.get<User>(`${environment.urlApi}user/profile`).pipe(
      catchError(this.handleError)
    )
  }

  private handleError(e:HttpErrorResponse){
    if(e.status===0){
      console.error('se ha produciodo un error ', e.error)
    }
    else {
      console.log('Backend retornó el código de estado ', e)
    }
    return throwError(()=>new Error('Algo falló. Por favor intente nuevamente.'));
}
}
