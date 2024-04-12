import { Component, OnInit } from '@angular/core';
import { userAuthService } from 'src/app/service/user-auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userFirstName: String;
  userLastName: String;
  userEmail: String;
  userName: String;

  constructor(private userAuthService: userAuthService, private router: Router,) { }

  ngOnInit() {
    this.userFirstName=this.userAuthService.getFirstName();
    this.userLastName=this.userAuthService.getLastName();
    this.userName=this.userAuthService.getUserName();
    this.userEmail=this.userAuthService.getUserEmail();
  }

  navigateToEdit(userId: String){
    this.router.navigate(['/edit-user-profile', userId]);
  }

}
