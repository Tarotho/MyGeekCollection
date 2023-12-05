import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../../../services/user/interfaces/user";
import {AuthService} from "../../../../services/auth/auth.service";
import {UserService} from "../../../../services/user/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{
  errorMessage:String="";
  user?:User;

  constructor(private userService:UserService, private auth:AuthService) {
    this.userService.getUser().subscribe({
      next: (userData)=> {
        this.user = userData;
      }
    })
  }

  ngOnInit(): void {

  }


  logOut(): void {
    this.auth.logOut();
  }

}
