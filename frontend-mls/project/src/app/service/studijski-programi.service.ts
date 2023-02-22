import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { StudijskiProgram, StudijskiProgramPage } from '../model/studijski-program';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class StudijskiProgramiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { }


  getAll(){
    return this.client.get<StudijskiProgramPage<StudijskiProgram>>(`${this.baseUrl}/studijskiProgrami`)
  }

  getOne(id : number){
    return this.client.get<StudijskiProgram[]>(`${this.baseUrl}/studijskiProgrami/${id}`)
  }

  create(studijskiProgram : StudijskiProgram){
    return this.client.post(`${this.baseUrl}/studijskiProgrami`, studijskiProgram)
  }

  update(id:number, studijskiProgram : StudijskiProgram){
    return this.client.put<StudijskiProgram[]>(`${this.baseUrl}/studijskiProgrami/${id}`, studijskiProgram)
  }

  delete(id:number){
    return this.client.delete<StudijskiProgram[]>(`${this.baseUrl}/studijskiProgrami/${id}`)
  }
}
