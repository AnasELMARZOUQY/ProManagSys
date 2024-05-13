import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { userService } from 'src/app/service/userService';
import { userAuthService } from 'src/app/service/user-auth';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit, OnDestroy {
  constructor( private router: Router, private userService: userService, private userAuthService: userAuthService) {}

  ngOnInit() {
  }

  ngOnDestroy() {
  }
  

  login(loginForm: NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        this.userAuthService.setUserNames(response.user.userFirstName,response.user.userLastName);
        this.userAuthService.setUserName(response.user.userName);
        this.userAuthService.setUserEmail(response.user.userEmail);
        this.userAuthService.setRole(response.user.role);
        this.userAuthService.setToken(response.jwtToken);
        this.router.navigate(['/projet']);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  navigateToRegister(){
    this.router.navigate(['/register']);
  }



}
