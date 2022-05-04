import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Ishod } from '../model/ishod';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class IshodiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<Ishod[]>(`${this.baseUrl}/ishodi`)
  }

  getOne(id : number){
    return this.client.get<Ishod[]>(`${this.baseUrl}/ishodi/${id}`)
  }

  create(smer : Ishod){
    return this.client.post(`${this.baseUrl}/ishodi`, smer)
  }

  update(id:number, smer : Ishod){
    return this.client.put<Ishod[]>(`${this.baseUrl}/ishodi/${id}`, smer)
  }

  delete(id:number){
    return this.client.delete<Ishod[]>(`${this.baseUrl}/ishodi/${id}`)
  }
}
