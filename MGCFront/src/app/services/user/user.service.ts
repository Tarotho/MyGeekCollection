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

  getUserList():Observable<User[]>{
    return this.httpClient.get<User[]>(`${environment.urlApi}'user`)
  }

  getUser():Observable<User>{
    return this.httpClient.get<User>(`${environment.urlApi}user/2`).pipe(
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
