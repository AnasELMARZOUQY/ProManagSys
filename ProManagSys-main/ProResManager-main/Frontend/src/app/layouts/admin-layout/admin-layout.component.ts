import { Component, OnInit } from '@angular/core';
import { userAuthService } from 'src/app/service/user-auth';


@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.scss']
})


export class AdminLayoutComponent implements OnInit {

  constructor(private userAuthService: userAuthService) { }

  ngOnInit() {
  }

  isloggedIn(){
    return this.userAuthService.isLoggedIn();
  }
}
