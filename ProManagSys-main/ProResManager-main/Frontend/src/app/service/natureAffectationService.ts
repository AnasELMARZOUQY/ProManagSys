import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { natureAffectation } from 'src/models/natureAffectation';
import { userAuthService } from './user-auth';

@Injectable({
    providedIn: 'root'
})

export class natureAffectationService {

    readonly API_URL = 'http://localhost:8082/NatureDaffectation';
  constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }

  private createHeaders(): HttpHeaders {
    const jwtToken = this.userAuthService.getToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${jwtToken}`
    });
    return headers;
  }

  getAllNatureAffectation(): Observable<natureAffectation[]> {
    const headers = this.createHeaders();
    return this.httpClient.get<natureAffectation[]>(`${this.API_URL}/GetallNatureDaffectation`, { headers });
  }

  addNatureAffectation(natureAffectation : any) {
    const headers = this.createHeaders();
    return this.httpClient.post(`${this.API_URL}/addNatureDaffectation`, natureAffectation, { headers })
  }

}