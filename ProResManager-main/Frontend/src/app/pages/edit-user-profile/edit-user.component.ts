import { Component, OnInit } from '@angular/core';
import { userService } from 'src/app/service/userService';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { userAuthService } from 'src/app/service/user-auth';


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})


export class EditUserComponent implements OnInit{

  userToEdit: any = {};

  constructor(private route: ActivatedRoute,private userService: userService, private toastr: ToastrService,private router: Router, private userAuthService: userAuthService){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      console.log("hh",params['iduser']);
      const userId = params['iduser'];
      this.userService.getUserById(userId).subscribe(userData => {
        this.userToEdit = userData;
      });
    });
    console.log(this.userToEdit.userName);
  }

  onSubmit() {
    this.userService.updateUser(this.userToEdit, this.userToEdit.userName).subscribe(
      (updatedUser: any) => {
        this.userAuthService.clear();
        this.router.navigate(['/login']);
        const messageFromApi = "Profile updated successfully, you have to login again ";
        this.toastr.success(messageFromApi);
      },
      (error: any) => {
        const messageFromApi = error.error.message;
        this.toastr.error(messageFromApi);
      }
    );
  }

  

}
