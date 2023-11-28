import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8081/";

  constructor(private httpClient : HttpClient) { }

  getUserList():Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}api/v1/users`)
  }

  getUser(id:number):Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}api/v1/users/${id}`)
  }
}
