import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Token } from '../model/token';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  token : null | string = null;
  user : any = null;
  rolesSubject: BehaviorSubject<Set<string>> = new BehaviorSubject<Set<string>>(new Set([]));
  loggedOut = false;

  constructor(private client : HttpClient) { }

  login(user:User){
    return this.client.post<Token>(`${this.baseUrl}/login`, user).pipe(
      tap(token => {
        this.token = token.token;
        this.user = JSON.parse(atob(token.token.split(".")[1]));
      })
    );
  }

  logout(): void {
    this.token = null;
    this.user = null;
    this.rolesSubject.next(new Set<string>([]));
    this.loggedOut = true;
  }

  //Za proveru prava pristupa rutiranja
  validateRoles(roles: string[]): boolean {
    if (this.user) {
      // @ts-ignore
      const userRoles = new Set(this.user.roles);
      for (const r of roles) {
        if (userRoles.has(r)) {
          return true;
        }
      }
    }
    return false;
  }
}
