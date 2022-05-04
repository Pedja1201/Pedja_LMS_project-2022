import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RealizacijaPredmeta } from '../model/realizacija-predmeta';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class RealizacijePredmetaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<RealizacijaPredmeta[]>(`${this.baseUrl}/realizacijePredmeta`)
  }

  getOne(id : number){
    return this.client.get<RealizacijaPredmeta[]>(`${this.baseUrl}/realizacijePredmeta/${id}`)
  }

  create(realizacijaPredmeta : RealizacijaPredmeta){
    return this.client.post(`${this.baseUrl}/realizacijePredmeta`, realizacijaPredmeta)
  }

  update(id:number, realizacijaPredmeta : RealizacijaPredmeta){
    return this.client.put<RealizacijaPredmeta[]>(`${this.baseUrl}/realizacijePredmeta/${id}`, realizacijaPredmeta)
  }

  delete(id:number){
    return this.client.delete<RealizacijaPredmeta[]>(`${this.baseUrl}/realizacijePredmeta/${id}`)
  }
}
