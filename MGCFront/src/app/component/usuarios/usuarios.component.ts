import {Component, OnInit} from '@angular/core';
import {User} from "../../services/user/interfaces/user";
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit{

  user:User[];
  userId:number | undefined;

  constructor(private userService:UserService, private router:Router) {}

  ngOnInit(): void {
    this.getUsers();
  }

private getUsers(){
    this.userService.getUserList().subscribe(data =>{this.user=data})
}

  userLogin(userId: number | undefined) {
    this.userId = userId;
    console.log('Usuario seleccionado con ID:', userId);
    this.router.navigate(['/profile'])
  }

}
