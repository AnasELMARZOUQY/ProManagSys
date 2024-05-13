import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { userAuthService } from './user-auth';
import { user } from 'src/models/user';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })

 
export class userService {

    
    readonly API_URL = 'http://localhost:8082';
    requestHeader = new HttpHeaders(
        {"No-Auth": "True"}
    );
    constructor(private httpClient: HttpClient, private userAuthService: userAuthService){}

    private createHeaders(): HttpHeaders {
        const jwtToken = this.userAuthService.getToken();
        const headers = new HttpHeaders({
          Authorization: `Bearer ${jwtToken}`
        });
        return headers;
    }

    getAllUsers(): Observable<user[]> {
        const headers = this.createHeaders();
        return this.httpClient.get<user[]>(`${this.API_URL}/user/getAllUsers`, { headers });
    }

    getUserById(userId: any): Observable<any> {
        const headers = this.createHeaders();
        const url = `${this.API_URL}/user/getUser/${userId}`; 
        return this.httpClient.get(url, { headers });
    }
    
    updateUser(user: user, userId: String) {
        const headers = this.createHeaders();
        const url = `${this.API_URL}/user/update/${userId}`;
        return this.httpClient.put(url, user, { headers });
    }

    deleteUser(userId: String){
        const headers = this.createHeaders();
        const url = `${this.API_URL}/user/removeUser/${userId}`;
        return this.httpClient.delete(url,{headers});
    }

    public login(loginData){
        return this.httpClient.post(`${this.API_URL}/authenticate`, loginData,{headers: this.requestHeader});
    }

    public registerNewUser(user: any){
        const url = `${this.API_URL}/user/registerNewUser`;
        return this.httpClient.post(url , user );
    }
}