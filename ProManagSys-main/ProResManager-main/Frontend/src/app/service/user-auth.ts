import { Injectable } from '@angular/core';
import { role } from 'src/models/role';



@Injectable({
    providedIn: 'root'
  })

 
export class userAuthService {

    role: role;

    constructor(){}

    public isAdmin(): boolean{
        this.role = this.getRole();
        return this.role.roleName=="Admin";
    }

    public isManager(): boolean{
        this.role = this.getRole();
        return this.role.roleName=="Manager";
    }

    public setUserInfo(userName: string, firstName: string, lastName: string, userEmail: string){
        localStorage.setItem('firstName',firstName);
        localStorage.setItem('lastName',lastName);
        localStorage.setItem('username',userName);
        localStorage.setItem('email',userEmail);
    }

    public setUserNames(firstName: string, lastName: string){
        localStorage.setItem('firstName',firstName);
        localStorage.setItem('lastName',lastName);
    }

    public setUserName(userName: string){
        localStorage.setItem('userName',userName);
    }

    public setUserEmail(userEmail: string){
        localStorage.setItem('userEmail',userEmail);
    }

    public getFirstName(){
        return localStorage.getItem('firstName');
    }

    public getLastName(){
        return localStorage.getItem('lastName');
    }

    public getUserName(){
        return localStorage.getItem('userName');
    }

    public getUserEmail(){
        return localStorage.getItem('userEmail');
    }

    public setRole(role: role){
        localStorage.setItem('role',JSON.stringify(role));
    }

    public  getRole(): any {
        return JSON.parse(localStorage.getItem('role'));
    }

    public setToken(jwtToken: string){
        localStorage.setItem("jwtToken",jwtToken);
    }

    public getToken(): string {
        return localStorage.getItem('jwtToken');
    }

    public clear(){
        localStorage.clear();
    }

    public isLoggedIn(){
        return this.getRole() && this.getToken();
    }
}