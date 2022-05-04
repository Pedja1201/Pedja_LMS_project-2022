import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Fakultet } from '../model/fakultet';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class FakultetiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<Fakultet[]>(`${this.baseUrl}/fakulteti`)
  }

  getOne(id : number){
    return this.client.get<Fakultet[]>(`${this.baseUrl}/fakulteti/${id}`)
  }

  create(fakultet : Fakultet){
    return this.client.post(`${this.baseUrl}/fakulteti`, fakultet)
  }

  update(id:number, fakultet : Fakultet){
    return this.client.put<Fakultet[]>(`${this.baseUrl}/fakulteti/${id}`, fakultet)
  }

  delete(id:number){
    return this.client.delete<Fakultet[]>(`${this.baseUrl}/fakulteti/${id}`)
  }
  
}
