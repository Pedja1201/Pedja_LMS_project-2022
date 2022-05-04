import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Zvanje } from '../model/zvanje';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class ZvanjaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<Zvanje[]>(`${this.baseUrl}/zvanja`)
  }

  getOne(id : number){
    return this.client.get<Zvanje[]>(`${this.baseUrl}/zvanja/${id}`)
  }

  create(zvanje : Zvanje){
    return this.client.post(`${this.baseUrl}/zvanja`, zvanje)
  }

  update(id:number, zvanje : Zvanje){
    return this.client.put<Zvanje[]>(`${this.baseUrl}/zvanja/${id}`, zvanje)
  }

  delete(id:number){
    return this.client.delete<Zvanje[]>(`${this.baseUrl}/zvanja/${id}`)
  }
}
