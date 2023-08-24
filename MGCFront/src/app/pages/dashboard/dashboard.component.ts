import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  userLoginOn:boolean=false;

  constructor(private auth:AuthService) {
  }

  ngOnInit(): void {
    this.auth.currentUserLoginOn.subscribe({
      next:(userLoginOn)=>{this.userLoginOn=userLoginOn}
    })
  }




}
