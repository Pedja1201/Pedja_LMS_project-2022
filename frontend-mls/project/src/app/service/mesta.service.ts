import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Mesto, MestoPage } from '../model/mesto';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class MestaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<MestoPage<Mesto>>(`${this.baseUrl}/mesta`)
  }

  getOne(id : number){
    return this.client.get<Mesto[]>(`${this.baseUrl}/mesta/${id}`)
  }

  create(mesto : Mesto){
    return this.client.post(`${this.baseUrl}/mesta`, mesto)
  }

  update(id:number, mesto : Mesto){
    return this.client.put<Mesto[]>(`${this.baseUrl}/mesta/${id}`, mesto)
  }

  delete(id:number){
    return this.client.delete<Mesto[]>(`${this.baseUrl}/mesta/${id}`)
  }
}
