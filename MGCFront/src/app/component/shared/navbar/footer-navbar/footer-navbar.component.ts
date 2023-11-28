import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-footer-navbar',
  templateUrl: './footer-navbar.component.html',
  styleUrls: ['./footer-navbar.component.css']
})
export class FooterNavbarComponent implements OnInit{
  userLoginOn:boolean=false;



  constructor(private auth:AuthService) {
  }

  ngOnInit(): void {
    this.auth.currentUserLoginOn.subscribe({
      next:(userLoginOn)=>{this.userLoginOn=userLoginOn}
    })

  }

  logOut(){
    this.auth.currentUserLoginOn.next(false)
  }
}
