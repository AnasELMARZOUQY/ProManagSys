 import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ressource } from 'src/models/ressource';
import { userAuthService } from './user-auth';


@Injectable({

  providedIn: 'root'

})

export class RessourceService {


  readonly API_URL = 'http://localhost:8082/Ressource';
  constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }

  private createHeaders(): HttpHeaders {
    const jwtToken = this.userAuthService.getToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${jwtToken}`
    });
    return headers;
  }

  getAllRessources(): Observable<Ressource[]> {
    const headers = this.createHeaders();
    return this.httpClient.get<Ressource[]>(`${this.API_URL}/getallRessources`, { headers });
  }

  updateRessource(ressource: Ressource, ressourceId: number) {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/update/${ressourceId}`;
    return this.httpClient.put(url, ressource, { headers });
  }


  addRessourcewithIdUser(Ressource : any) {
    const headers = this.createHeaders();
    return this.httpClient.post(`${this.API_URL}/addressource/${this.userAuthService.getUserName()}`, Ressource, { headers })
  }

 
  getRessourceById(ressourcetId: any): Observable<any> {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/GetRessource/${ressourcetId}`; 
    return this.httpClient.get(url, { headers });
  }

  deletRessource(resourceId: Number) {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/removeRessource/${this.userAuthService.getUserName()}/${resourceId}`;
    return this.httpClient.delete(url, { headers });
  }

 

}

