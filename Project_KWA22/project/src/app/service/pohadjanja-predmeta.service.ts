import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PohadjanjePredmeta, PohadjanjePredmetaPage } from '../model/pohadjanje-predmeta';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PohadjanjaPredmetaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<PohadjanjePredmetaPage<PohadjanjePredmeta>>(`${this.baseUrl}/pohadjanjaPredmeta`)
  }

  getOne(id : number){
    return this.client.get<PohadjanjePredmeta[]>(`${this.baseUrl}/pohadjanjaPredmeta/${id}`)
  }

  create(pohadjanjePredmeta : PohadjanjePredmeta){
    return this.client.post(`${this.baseUrl}/pohadjanjaPredmeta`, pohadjanjePredmeta)
  }

  update(id:number, pohadjanjePredmeta : PohadjanjePredmeta){
    return this.client.put<PohadjanjePredmeta[]>(`${this.baseUrl}/pohadjanjaPredmeta/${id}`, pohadjanjePredmeta)
  }

  delete(id:number){
    return this.client.delete<PohadjanjePredmeta[]>(`${this.baseUrl}/pohadjanjaPredmeta/${id}`)
  }
}
