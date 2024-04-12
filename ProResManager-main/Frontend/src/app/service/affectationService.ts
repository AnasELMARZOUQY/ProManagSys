import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { affectation } from 'src/models/affectation';
import { userAuthService } from './user-auth';


@Injectable({
    providedIn: 'root'
  })

export class AffectationService{


    readonly API_URL = 'http://localhost:8082/affectation';
    constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }

    private createHeaders(): HttpHeaders {
      const jwtToken = this.userAuthService.getToken();
      const headers = new HttpHeaders({
        Authorization: `Bearer ${jwtToken}`
      });
      return headers;
    }

    getAllAffectations(): Observable<affectation[]>{
      const headers = this.createHeaders();
      return this.httpClient.get<affectation[]>(`${this.API_URL}/getallaffectations`, { headers });
    }

    getAffectationById(affectationId: any): Observable<any> {
      const headers = this.createHeaders();
      const url = `${this.API_URL}/GetAffectation/${affectationId}`; 
      return this.httpClient.get(url, { headers });
    }

    addAffectationWithIdUser(affectation: affectation){
      const headers = this.createHeaders();
      return this.httpClient.post(`${this.API_URL}/addaffectation/${this.userAuthService.getUserName()}`,affectation, { headers });
    }

    updateAffectation(affectation: affectation, affectationId: number) {
      const headers = this.createHeaders();
      const url = `${this.API_URL}/update/${affectationId}`;
      return this.httpClient.put(url, affectation, { headers });
    }


    deleteAffectation(affectationId: Number) {
      const headers = this.createHeaders();
      const url = `${this.API_URL}/deleteaffectation/${affectationId}`;
      return this.httpClient.delete(url, { headers });
    }



}