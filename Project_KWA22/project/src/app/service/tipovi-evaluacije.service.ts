import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TipEvaluacije, TipEvaluacijePage } from '../model/tip-evaluacije';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TipoviEvaluacijeService {
  private baseUrl = environment.baseUrl //Dobavljanje url tipoviEvaluacije da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<TipEvaluacijePage<TipEvaluacije>>(`${this.baseUrl}/tipoviEvaluacije`)
  }

  getOne(id : number){
    return this.client.get<TipEvaluacije[]>(`${this.baseUrl}/tipoviEvaluacije/${id}`)
  }

  create(tipEvaluacije : TipEvaluacije){
    return this.client.post(`${this.baseUrl}/tipoviEvaluacije`, tipEvaluacije)
  }

  update(id:number, tipEvaluacije : TipEvaluacije){
    return this.client.put<TipEvaluacije[]>(`${this.baseUrl}/tipoviEvaluacije/${id}`, tipEvaluacije)
  }

  delete(id:number){
    return this.client.delete<TipEvaluacije[]>(`${this.baseUrl}/tipoviEvaluacije/${id}`)
  }
}
