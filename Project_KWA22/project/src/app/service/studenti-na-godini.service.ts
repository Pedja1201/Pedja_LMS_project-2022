import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StudentNaGodini } from '../model/student-na-godini';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class StudentiNaGodiniService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { } //Login

  
  getAll(){
    return this.client.get<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini`)
  }

  getOne(id : number){
    return this.client.get<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini/${id}`)
  }

  create(studentNaGodini : StudentNaGodini){
    return this.client.post(`${this.baseUrl}/studentiNaGodini`, studentNaGodini)
  }

  update(id:number, studentNaGodini : StudentNaGodini){
    return this.client.put<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini/${id}`, studentNaGodini)
  }

  delete(id:number){
    return this.client.delete<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini/${id}`)
  }
  pretrazi(parametri: any = undefined) {
    if (parametri == undefined) {
      return this.client.get<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini`);
    }
    return this.client.get<StudentNaGodini[]>(`${this.baseUrl}/studentiNaGodini`).pipe(
      map(studenti => {
        return studenti.filter(student => {
          let rezultat = true;
          if (student["brojIndeksa"] && parametri["brojIndeksa"]) {
            rezultat &&= student["brojIndeksa"] == parametri["brojIndeksa"];
          }
          return rezultat;
        });
      })
    );
  }
}
