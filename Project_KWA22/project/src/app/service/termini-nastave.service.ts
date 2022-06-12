import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TerminNastave } from '../model/termin-nastave';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TerminiNastaveService {
  private baseUrl = environment.baseUrl //Dobavljanje url terminiNastave da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }

  getAll(){
    return this.client.get<TerminNastave[]>(`${this.baseUrl}/terminiNastave`)
  }

  getOne(id : number){
    return this.client.get<TerminNastave[]>(`${this.baseUrl}/terminiNastave/${id}`)
  }

  create(terminNastave : TerminNastave){
    return this.client.post(`${this.baseUrl}/terminiNastave`, terminNastave)
  }

  update(id:number, terminNastave : TerminNastave){
    return this.client.put<TerminNastave[]>(`${this.baseUrl}/terminiNastave/${id}`, terminNastave)
  }

  delete(id:number){
    return this.client.delete<TerminNastave[]>(`${this.baseUrl}/terminiNastave/${id}`)
  }
}
