import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../../../services/interfaces/user";
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{
  userData?:User;


  constructor(private auth:AuthService) {
  }

  ngOnInit(): void {
    this.auth.currentUserData.subscribe({
      next: (userData)=>this.userData=userData
        }
    )
  }

}
