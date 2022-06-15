import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Polaganje, PolaganjePage } from '../model/polaganje';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PolaganjaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<PolaganjePage<Polaganje>>(`${this.baseUrl}/polaganja`)
  }

  getOne(id : number){
    return this.client.get<Polaganje[]>(`${this.baseUrl}/polaganja/${id}`)
  }

  create(polaganje : Polaganje){
    return this.client.post(`${this.baseUrl}/polaganja`, polaganje)
  }

  update(id:number, polaganje : Polaganje){
    return this.client.put<Polaganje[]>(`${this.baseUrl}/polaganja/${id}`, polaganje)
  }

  delete(id:number){
    return this.client.delete<Polaganje[]>(`${this.baseUrl}/polaganja/${id}`)
  }
}
