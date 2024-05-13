import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { sessionService } from 'src/app/service/sessionService';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.scss']
})
export class EditSessionComponent {

  sessionToEdit: any = {};

  

  constructor(private route: ActivatedRoute, private sessionService: sessionService, private router: Router, private toastr: ToastrService) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const sessionId = +params['idsession'];
      this.sessionService.getSessionById(sessionId).subscribe(sessionData => {
        sessionData.dateDebutSession = new Date(sessionData.dateDebutSession).toISOString().split('T')[0];
        sessionData.dateFinSession = new Date(sessionData.dateFinSession).toISOString().split('T')[0];
        this.sessionToEdit = sessionData;
      });
    });
  }


  onSubmit() {
    console.log("hello");
    console.log( this.sessionToEdit );
    
    this.sessionService.updateSession(this.sessionToEdit, this.sessionToEdit.idsession).subscribe(
      updatedSession => {
        console.log('session updated:', updatedSession);
        this.router.navigate(['session'])
        const messageFromApi = "Session updated successfully ";
        this.toastr.success(messageFromApi);
      },
      error => {
        console.error('Error updating session:', error);
        const messageFromApi = error.error.message;
        this.toastr.error(messageFromApi);
      }
    );
  }



}
