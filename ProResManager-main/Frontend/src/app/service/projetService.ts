import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projet } from 'src/models/projet';
import { userAuthService } from './user-auth';
 

@Injectable({
  providedIn: 'root'
})

export class ProjetService  {


  readonly API_URL = 'http://localhost:8082/projet';
  constructor(private httpClient: HttpClient,private userAuthService: userAuthService) { }
  
  
  private createHeaders(): HttpHeaders {
    const jwtToken = this.userAuthService.getToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${jwtToken}`
    });
    return headers;
  }

  
  getAllProjet(): Observable<Projet[]> {
    const headers = this.createHeaders();
    return this.httpClient.get<Projet[]>(`${this.API_URL}/GetallProject`, { headers });
  }

  updateProjet(projet: Projet, projectId: number) {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/updates/${projectId}`;
    return this.httpClient.put(url, projet, { headers });
  }

  addProjetwithIdUser(Projet : any) {
    const headers = this.createHeaders();
    return this.httpClient.post(`${this.API_URL}/addprojet/${this.userAuthService.getUserName()}`, Projet, { headers })
  }

  getProjectById(projectId: any): Observable<any> {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/GetProjet/${projectId}`; 
    return this.httpClient.get(url, { headers });
  }

  deleteProject( projectId: Number) {
    const headers = this.createHeaders();
    const url = `${this.API_URL}/removeProjet/${this.userAuthService.getUserName()}/${projectId}`;
    return this.httpClient.delete(url, { headers });
  }

}

