import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { session } from 'src/models/session';
import { userAuthService } from './user-auth';

 

@Injectable({
  providedIn: 'root'
})

export class sessionService {

    readonly API_URL = 'http://localhost:8082/Session';
    constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }

    private createHeaders(): HttpHeaders {
       const jwtToken = this.userAuthService.getToken();
       const headers = new HttpHeaders({
          Authorization: `Bearer ${jwtToken}`
        });
       return headers;
    }
    

    isActive: boolean;

   checkActiveSession() {
    this.getActiveSession().subscribe(
      (response: any) => {
        if (response && response.message === "no active session") {
          this.isActive=false;
          console.log("No active session");
        } else {
          console.log("Active session exists:", response);
          this.isActive=true;
        }
      },
      (error: any) => {
        console.error("Error:", error);
        return 
      }
    );
  }
    
    getAllSession(): Observable<session[]>{
        const headers = this.createHeaders();
        return this.httpClient.get<session[]>(`${this.API_URL}/GetallSession`, { headers });
    }

    getSessionById(sessionId: Number): Observable<any> {
        const headers = this.createHeaders();
        const url = `${this.API_URL}/GetSession/${sessionId}`; 
        return this.httpClient.get(url, { headers });
    }

    getActiveSession(): any {
        const headers = this.createHeaders();
        return this.httpClient.get(`${this.API_URL}/getActiveSession`, { headers });
    }
    
    addSession(Session: any){
        const headers = this.createHeaders();
        const url = `${this.API_URL}/addSession/${this.userAuthService.getUserName()}`;
        return this.httpClient.post(url, Session, { headers });
    }
    updateSession(session: any, sessionId: number) {
        const headers = this.createHeaders();
        const url = `${this.API_URL}/update/${sessionId}`;
        return this.httpClient.put(url, session, { headers });
    }


    deletesSession(sessionId: number){
      const headers = this.createHeaders();
      const url = `${this.API_URL}/deleteSession/${sessionId}`;
      return this.httpClient.delete(url, {headers});
    }

}
