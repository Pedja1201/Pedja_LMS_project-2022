import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { EvaluacijaZnanja, EvaluacijaZnanjaPage } from '../model/evaluacija-znanja';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class EvaluacijeZnanjaService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<EvaluacijaZnanjaPage<EvaluacijaZnanja>>(`${this.baseUrl}/evaluacijeZnanja`)
  }

  getOne(id : number){
    return this.client.get<EvaluacijaZnanja[]>(`${this.baseUrl}/evaluacijeZnanja/${id}`)
  }

  create(evaluacijaZnanja : EvaluacijaZnanja){
    return this.client.post(`${this.baseUrl}/evaluacijeZnanja`, evaluacijaZnanja)
  }

  update(id:number, evaluacijaZnanja : EvaluacijaZnanja){
    return this.client.put<EvaluacijaZnanja[]>(`${this.baseUrl}/evaluacijeZnanja/${id}`, evaluacijaZnanja)
  }

  delete(id:number){
    return this.client.delete<EvaluacijaZnanja[]>(`${this.baseUrl}/evaluacijeZnanja/${id}`)
  }
}
