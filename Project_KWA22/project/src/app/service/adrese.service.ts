import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Adresa } from '../model/adresa';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AdreseService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<Adresa[]>(`${this.baseUrl}/adrese`)
  }

  getOne(id : number){
    return this.client.get<Adresa[]>(`${this.baseUrl}/adrese/${id}`)
  }

  create(adresa : Adresa){
    return this.client.post(`${this.baseUrl}/adrese`, adresa)
  }

  update(id:number, adresa : Adresa){
    return this.client.put<Adresa[]>(`${this.baseUrl}/adrese/${id}`, adresa)
  }

  delete(id:number){
    return this.client.delete<Adresa[]>(`${this.baseUrl}/adrese/${id}`)
  }
}
