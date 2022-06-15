import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IshodNastave, IshodNastavePage } from '../model/ishod-nastave';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class IshodiNastaveService {
  private baseUrl = environment.baseUrl //Dobavljanje url ishodNastave da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<IshodNastavePage<IshodNastave>>(`${this.baseUrl}/ishodiNastave`)
  }

  getOne(id : number){
    return this.client.get<IshodNastave[]>(`${this.baseUrl}/ishodiNastave/${id}`)
  }

  create(ishodNastave : IshodNastave){
    return this.client.post(`${this.baseUrl}/ishodiNastave`, ishodNastave)
  }

  update(id:number, ishodNastave : IshodNastave){
    return this.client.put<IshodNastave[]>(`${this.baseUrl}/ishodiNastave/${id}`, ishodNastave)
  }

  delete(id:number){
    return this.client.delete<IshodNastave[]>(`${this.baseUrl}/ishodiNastave/${id}`)
  }
}
