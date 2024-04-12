import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { userAuthService

} from 'src/app/service/user-auth';
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/projet', title: 'Projets',  icon: 'ni-collection', class: '' },
    { path: '/ressource', title: 'Ressources',  icon: 'ni ni-folder-17', class: '' },
    { path: '/tables', title: 'Affectations',  icon:'ni-bullet-list-67 text-red', class: '' },
    { path: '/user-profile', title: 'Profile',  icon:'ni-single-02 text-yellow', class: '' },
    { path: '/session', title: 'Session',  icon:'ni-circle-08 text-pink', class: '' },
    { path: '/userslist', title: 'Liste des Managers',  icon:'ni-single-02 text-blue', class: '' }
];




@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})



export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;


  constructor(private router: Router, private userAuthService: userAuthService) { }

  isAdmin(){
    return this.userAuthService.isAdmin();
  }

  
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }
}
