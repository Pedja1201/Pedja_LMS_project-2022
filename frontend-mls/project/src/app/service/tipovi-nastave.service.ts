import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TipNastave, TipNastavePage } from '../model/tip-nastave';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TipoviNastaveService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<TipNastavePage<TipNastave>>(`${this.baseUrl}/tipoviNastave`)
  }

  getOne(id : number){
    return this.client.get<TipNastave[]>(`${this.baseUrl}/tipoviNastave/${id}`)
  }

  create(tipNastave : TipNastave){
    return this.client.post(`${this.baseUrl}/tipoviNastave`, tipNastave)
  }

  update(id:number, tipNastave : TipNastave){
    return this.client.put<TipNastave[]>(`${this.baseUrl}/tipoviNastave/${id}`, tipNastave)
  }

  delete(id:number){
    return this.client.delete<TipNastave[]>(`${this.baseUrl}/tipoviNastave/${id}`)
  }
}
