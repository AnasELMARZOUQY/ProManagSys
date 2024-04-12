import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { sessionService } from 'src/app/service/sessionService';

@Component({
  selector: 'app-add-session',
  templateUrl: './add-session.component.html',
  styleUrls: ['./add-session.component.scss']
})


export class AddSessionComponent {

  newSession: any = {};

  constructor(private router: Router, private toastr: ToastrService, private sessionService: sessionService) {}

  onSubmit(){
    this.sessionService.addSession(this.newSession).subscribe(
      (response: any) => {
        this.newSession = {};
        this.router.navigate(['session']);
        const messageFromApi = response.message;
          this.toastr.success(messageFromApi);
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
        }
    );
  }
  
}
