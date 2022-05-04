import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { NastavnikNaRealizaciji } from '../model/nastavnik-na-realizaciji';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class NastavniciNaRealizacijiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { } //Login

  
  getAll(){
    return this.client.get<NastavnikNaRealizaciji[]>(`${this.baseUrl}/nastavniciNaRealizaciji`)
  }

  getOne(id : number){
    return this.client.get<NastavnikNaRealizaciji[]>(`${this.baseUrl}/nastavniciNaRealizaciji/${id}`)
  }

  create(nastavnikNaRealizaciji : NastavnikNaRealizaciji){
    return this.client.post(`${this.baseUrl}/nastavniciNaRealizaciji`, nastavnikNaRealizaciji)
  }

  update(id:number, nastavnikNaRealizaciji : NastavnikNaRealizaciji){
    return this.client.put<NastavnikNaRealizaciji[]>(`${this.baseUrl}/nastavniciNaRealizaciji/${id}`, nastavnikNaRealizaciji)
  }

  delete(id:number){
    return this.client.delete<NastavnikNaRealizaciji[]>(`${this.baseUrl}/nastavniciNaRealizaciji/${id}`)
  }
}
