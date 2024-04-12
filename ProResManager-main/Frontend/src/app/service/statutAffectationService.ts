import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { statutAffectation } from 'src/models/statutAffectation';
import { userAuthService } from './user-auth';

@Injectable({
    providedIn: 'root'
})

export class statutAffectationService {

  readonly API_URL = 'http://localhost:8082/StatuDaff';
  constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }

  private createHeaders(): HttpHeaders {
    const jwtToken = this.userAuthService.getToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${jwtToken}`
    });
    return headers;
  }

  getAllStatutAffectation(): Observable<statutAffectation[]> {
    const headers = this.createHeaders();
    return this.httpClient.get<statutAffectation[]>(`${this.API_URL}/GetallStatudaffectation`, { headers });
  }

  addStatutAffectation(statutAffectation : any) {
    const headers = this.createHeaders();
    return this.httpClient.post(`${this.API_URL}/addStatuDaffectation`, statutAffectation, { headers })
  }

}