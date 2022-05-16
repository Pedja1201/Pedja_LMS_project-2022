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
  loggedOut = false;  //Ispis da je uspesno izlogovan
  loggedIn = false;  //Za prikaz dugmica nakkon Login-a

  constructor(private client : HttpClient) { }

  login(user:User){
    return this.client.post<Token>(`${this.baseUrl}/login`, user).pipe(
      tap(token => {
        this.token = token.token;
        this.user = JSON.parse(atob(token.token.split(".")[1]));
        this.loggedIn=true; //Za prikaz dugmica nakon Login-a
      })
    );
  }

  logout(): void {
    this.token = null;
    this.user = null;
    this.rolesSubject.next(new Set<string>([]));
    this.loggedOut = true; //Ispis da je uspesno izlogovan
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

            //Za proveru prava sa dodatnim metodama any i all
  // validateRoles(roles: any, method="any") {
  //   if (this.user) {
  //     // @ts-ignore
  //     let userRoles = new Set(this.user.roles);
  //     roles=new Set(roles);
  //     let intersection = new Set();

  //     for(let r of roles){
  //       if(userRoles.has(r)){
  //         intersection.add(r);
  //       }
  //     }

  //     if(method == "any"){
  //       return intersection.size > 0;
  //     }else if(method == "all"){
  //       return intersection.size == roles.size;
  //     }

  //   }
  //   return false;
  // }


}
