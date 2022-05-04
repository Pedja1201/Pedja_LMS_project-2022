import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GodinaStudija } from '../model/godina-studija';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class GodineStudijaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<GodinaStudija[]>(`${this.baseUrl}/godineStudija`)
  }

  getOne(id : number){
    return this.client.get<GodinaStudija[]>(`${this.baseUrl}/godineStudija/${id}`)
  }

  create(godinaStudija : GodinaStudija){
    return this.client.post(`${this.baseUrl}/godineStudija`, godinaStudija)
  }

  update(id:number, godinaStudija : GodinaStudija){
    return this.client.put<GodinaStudija[]>(`${this.baseUrl}/godineStudija/${id}`, godinaStudija)
  }

  delete(id:number){
    return this.client.delete<GodinaStudija[]>(`${this.baseUrl}/godineStudija/${id}`)
  }
  
}
