import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Predmet, PredmetPage } from '../model/predmet';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PredmetiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<PredmetPage<Predmet>>(`${this.baseUrl}/predmeti`)
  }

  getOne(id : number){
    return this.client.get<Predmet[]>(`${this.baseUrl}/predmeti/${id}`)
  }

  create(predmet : Predmet){
    return this.client.post(`${this.baseUrl}/predmeti`, predmet)
  }

  update(id:number, predmet : Predmet){
    return this.client.put<Predmet[]>(`${this.baseUrl}/predmeti/${id}`, predmet)
  }

  delete(id:number){
    return this.client.delete<Predmet[]>(`${this.baseUrl}/predmeti/${id}`)
  }
}
