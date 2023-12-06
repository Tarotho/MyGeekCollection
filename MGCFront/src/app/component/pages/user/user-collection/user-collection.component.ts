import {Component, OnInit} from '@angular/core';
import {User} from "../../../../services/user/interfaces/user";
import {UserService} from "../../../../services/user/user.service";
import {AuthService} from "../../../../services/auth/auth.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-collection',
  templateUrl: './user-collection.component.html',
  styleUrls: ['./user-collection.component.css']
})
export class UserCollectionComponent implements OnInit {
  userId: number;
  user: any; // Ajusta el tipo según la estructura de tu modelo

  constructor(private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.userId = +params['id'];
      this.getUserProfile();
    });
  }

  getUserProfile(): void {
    this.userService.getUserById(this.userId).subscribe((user) => {
      this.user = user;
    });
  }
}
