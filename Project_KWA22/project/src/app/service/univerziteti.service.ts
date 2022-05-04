import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Univerzitet } from '../model/univerzitet';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UniverzitetiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<Univerzitet[]>(`${this.baseUrl}/univerziteti`)
  }

  getOne(id : number){
    return this.client.get<Univerzitet[]>(`${this.baseUrl}/univerziteti/${id}`)
  }

  create(univerzitet : Univerzitet){
    return this.client.post(`${this.baseUrl}/univerziteti`, univerzitet)
  }

  update(id:number, univerzitet : Univerzitet){
    return this.client.put<Univerzitet[]>(`${this.baseUrl}/univerziteti/${id}`, univerzitet)
  }

  delete(id:number){
    return this.client.delete<Univerzitet[]>(`${this.baseUrl}/univerziteti/${id}`)
  }
}
