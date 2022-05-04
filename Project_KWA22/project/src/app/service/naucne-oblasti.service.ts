import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { NaucnaOblast } from '../model/naucna-oblast';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class NaucneOblastiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<NaucnaOblast[]>(`${this.baseUrl}/naucneOblasti`)
  }

  getOne(id : number){
    return this.client.get<NaucnaOblast[]>(`${this.baseUrl}/naucneOblasti/${id}`)
  }

  create(naucnaOblast : NaucnaOblast){
    return this.client.post(`${this.baseUrl}/naucneOblasti`, naucnaOblast)
  }

  update(id:number, naucnaOblast : NaucnaOblast){
    return this.client.put<NaucnaOblast[]>(`${this.baseUrl}/naucneOblasti/${id}`, naucnaOblast)
  }

  delete(id:number){
    return this.client.delete<NaucnaOblast[]>(`${this.baseUrl}/naucneOblasti/${id}`)
  }
}
