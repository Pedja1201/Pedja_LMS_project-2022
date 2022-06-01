import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Nastavnik } from '../model/nastavnik';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class NastavniciService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { } //Login

  
  getAll(){
    return this.client.get<Nastavnik[]>(`${this.baseUrl}/nastavnici`)
  }

  getOne(id : number){
    return this.client.get<Nastavnik[]>(`${this.baseUrl}/nastavnici/${id}`)
  }

  create(nastavnik : Nastavnik){
    return this.client.post(`${this.baseUrl}/nastavnici`, nastavnik)
  }

  update(id:number, nastavnik : Nastavnik){
    return this.client.put<Nastavnik[]>(`${this.baseUrl}/nastavnici/${id}`, nastavnik)
  }

  delete(id:number){
    return this.client.delete<Nastavnik[]>(`${this.baseUrl}/nastavnici/${id}`)
  }
  pretrazi(parametri: any = undefined) {
    if (parametri == undefined) {
      return this.client.get<Nastavnik[]>(`${this.baseUrl}/nastavnici`);
    }
    return this.client.get<Nastavnik[]>(`${this.baseUrl}/nastavnici`).pipe(
      map(nastavnici => {
        return nastavnici.filter(nastavnik => {
          let rezultat = true;
          if (nastavnik["ime"] && parametri["ime"]) {
            rezultat &&= nastavnik["ime"] == parametri["ime"];
          }
          if (nastavnik["jmbg"] && parametri["jmbg"]) {
            rezultat &&= nastavnik["jmbg"] == parametri["jmbg"];
          }
          return rezultat;
        });
      })
    );
  }
}
