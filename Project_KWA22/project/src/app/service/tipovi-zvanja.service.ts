import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TipZvanja } from '../model/tip-zvanja';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TipoviZvanjaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<TipZvanja[]>(`${this.baseUrl}/tipoviZvanja`)
  }

  getOne(id : number){
    return this.client.get<TipZvanja[]>(`${this.baseUrl}/tipoviZvanja/${id}`)
  }

  create(tipZvanja : TipZvanja){
    return this.client.post(`${this.baseUrl}/tipoviZvanja`, tipZvanja)
  }

  update(id:number, tipZvanja : TipZvanja){
    return this.client.put<TipZvanja[]>(`${this.baseUrl}/tipoviZvanja/${id}`, tipZvanja)
  }

  delete(id:number){
    return this.client.delete<TipZvanja[]>(`${this.baseUrl}/tipoviZvanja/${id}`)
  }
}
