import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { userAuthService } from 'src/app/service/user-auth';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {


  userFirstName: String;
  userLastName: String ;
  userName: String;
  userEmail: String;

  public focus;
  public listTitles: any[];
  public location: Location;
  constructor(location: Location,  private element: ElementRef, private router: Router, private userAuthService: userAuthService) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    this.userFirstName=this.userAuthService.getFirstName();
    this.userLastName=this.userAuthService.getLastName();
    this.userEmail=this.userAuthService.getUserName();
    //this.userName=this.userAuthService.getEmail();
  }
  getTitle(){
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if(titlee.charAt(0) === '#'){
        titlee = titlee.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++){
        if(this.listTitles[item].path === titlee){
            return this.listTitles[item].title;
        }
    }
  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  
  public logout(){
    this.userAuthService.clear();
    this.router.navigate(['/login']);
  }

}
