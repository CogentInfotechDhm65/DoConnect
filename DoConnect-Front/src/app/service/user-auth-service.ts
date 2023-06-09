import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { tap } from "rxjs";
import { BehaviorSubject } from "rxjs";

import { User } from "../model/user";
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  public isLoggedInSubject = new BehaviorSubject<boolean>(this.isLoggedIn());

  constructor(private http: HttpClient) { }

  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles') as any);
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('jwtToken') as any;
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn(): boolean {
    return !!this.getRoles() && !!this.getToken();
  }
  
  public setIsLoggedIn(value: boolean) {
    this.isLoggedInSubject.next(value);
  }

  public isLoggedIn$(): Observable<boolean> {
    return this.isLoggedInSubject.asObservable();
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      return null;
    }
  }

  public setUser(user: User) {
    localStorage.setItem('user.id', user.id.toString());
    localStorage.setItem('user.username', user.username);
    localStorage.setItem('user.name', user.name);
    localStorage.setItem('user.userType', user.userType);
  }

  public getUserName(): string {
    return localStorage.getItem('user.username') as string;
  }

  public getUserUserType(): string {
    return localStorage.getItem('user.userType') as string;
  }

  public getUserId():string {
    return localStorage.getItem('user.id') as string ;
  }

  private apiURL = "http://localhost:8080/authenticate";

  logout() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('user.username');
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('jwtToken');
    return token !== null;
  }
}
