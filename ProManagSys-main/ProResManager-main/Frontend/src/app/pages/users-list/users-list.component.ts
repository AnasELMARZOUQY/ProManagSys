import { Component, OnInit } from '@angular/core';
import { userService } from 'src/app/service/userService';
import { user } from 'src/models/user';



@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})


export class UsersListComponent implements OnInit{
  
  users: user[]= [];

  constructor(private userService: userService){}
  
  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void{
    this.userService.getAllUsers().subscribe(
      (users: user[]) => {
        this.users = users; 
        console.log(this.users);
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }
}
