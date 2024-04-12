import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { userService } from 'src/app/service/userService';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})


export class RegisterComponent implements OnInit {

  newUser: any = {};
  
  constructor( private router: Router, private userService: userService, private toastr: ToastrService) { }

  ngOnInit() {
  }

  onSubmit(){
    this.userService.registerNewUser(this.newUser).subscribe(
      (response: any) => {
        this.newUser = {};
        this.router.navigate(['/login']);
        const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
        }
    );
  }

  navigateToLogIn(){
    this.router.navigate(['/login']);

  }
  

}
