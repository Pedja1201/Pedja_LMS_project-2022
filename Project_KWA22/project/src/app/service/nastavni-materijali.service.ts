import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { NastavniMaterijal, NastavniMaterijalPage } from '../model/nastavni-materijal';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class NastavniMaterijaliService {
  private baseUrl = environment.baseUrl //Dobavljanje url nastavniMaterijali da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<NastavniMaterijalPage<NastavniMaterijal>>(`${this.baseUrl}/nastavniMaterijali`)
  }

  getOne(id : number){
    return this.client.get<NastavniMaterijal[]>(`${this.baseUrl}/nastavniMaterijali/${id}`)
  }

  create(nastavniMaterijal : NastavniMaterijal){
    return this.client.post(`${this.baseUrl}/nastavniMaterijali`, nastavniMaterijal)
  }

  update(id:number, nastavniMaterijal : NastavniMaterijal){
    return this.client.put<NastavniMaterijal[]>(`${this.baseUrl}/nastavniMaterijali/${id}`, nastavniMaterijal)
  }

  delete(id:number){
    return this.client.delete<NastavniMaterijal[]>(`${this.baseUrl}/nastavniMaterijali/${id}`)
  }
}
