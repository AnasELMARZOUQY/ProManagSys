import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { sessionService } from 'src/app/service/sessionService';
import { userAuthService } from 'src/app/service/user-auth';
import { session } from 'src/models/session';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})


export class SessionComponent {


  activeSession : any ;
  sessions: session[] = [];

  constructor(private router: Router, private sessionService : sessionService, private userAuthService : userAuthService ,private toastr : ToastrService ) { }

  ngOnInit() {
    this.fetchActiveSession();
    this.fetchSessions();
  }


  isAdmin():boolean{
    return this.userAuthService.isAdmin();
  }

  isManager():boolean{
    return this.userAuthService.isManager();
  }

  fetchSessions(): void{
    this.sessionService.getAllSession().subscribe(
      (sessions: session[]) => {
        this.sessions = sessions; 
        console.log(sessions);
      },
      (error) => {
        console.error('Error fetching sessions:', error);
      }
    );
  }

  fetchActiveSession(): void {
    this.sessionService.getActiveSession().subscribe(
      (activeSession: any) => {
        activeSession.dateDebutSession = new Date(activeSession.dateDebutSession).toISOString().split('T')[0];
        activeSession.dateFinSession = new Date(activeSession.dateFinSession).toISOString().split('T')[0];
        console.log(activeSession);
        this.activeSession = activeSession; 
      },
      (error) => {
        console.error('Error fetching active session:', error);
      }
    );
  }

  navigateToTarget() {
    console.log("test ajout session");
    this.router.navigate(['/addsession']);
  }

  navigateToEdit(sessionId: Number){
    console.log("test edit session");
    this.router.navigate(['/editsession', sessionId]);
  }

  deleteSession(sessionId: number) {
    this.sessionService.deletesSession(sessionId)
      .subscribe(
        (response: any) => {
          console.log('session deleted successfully');
          this.fetchSessions();      
          const messageFromApi = response.message;
          this.toastr.success(messageFromApi);  
        },
        (error: any) => {
          const messageFromApi = error.error.message;
          this.toastr.error(messageFromApi);
          console.error('Error deleting session', error);
        }
      );
  }  

  getStyleForType(typeSession: string): object {
    if (typeSession === 'Active') {
      return { color: 'green' };
    } else if (typeSession === 'Scheduled') {
      return { color: '#FFD700' };
    } else if (typeSession === 'Inactive') {
      return { color: 'red' };
    }
    return {}; // Default style
  }
  
}
