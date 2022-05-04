import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Student } from '../model/student';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class StudentiService {
  private baseUrl = environment.baseUrl //Dobavljanje url adrese da ne kucamo rucno

  constructor(private client : HttpClient, private loginService : LoginService) { } //Login

  
  getAll(){
    return this.client.get<Student[]>(`${this.baseUrl}/studenti`)
  }

  getOne(id : number){
    return this.client.get<Student[]>(`${this.baseUrl}/studenti/${id}`)
  }

  create(student : Student){
    return this.client.post(`${this.baseUrl}/studenti`, student)
  }

  update(id:number, student : Student){
    return this.client.put<Student[]>(`${this.baseUrl}/studenti/${id}`, student)
  }

  delete(id:number){
    return this.client.delete<Student[]>(`${this.baseUrl}/studenti/${id}`)
  }
  pretrazi(parametri: any = undefined) {
    if (parametri == undefined) {
      return this.client.get<Student[]>(`${this.baseUrl}/studenti`);
    }
    return this.client.get<Student[]>(`${this.baseUrl}/studenti`).pipe(
      map(studenti => {
        return studenti.filter(student => {
          let rezultat = true;
          if (student["id"] && parametri["id"]) {
            rezultat &&= student["id"] == parametri["id"];
          }
          if (student["jmbg"] && parametri["jmbg"]) {
            rezultat &&= student["jmbg"] == parametri["jmbg"];
          }
          if (student["ime"] && parametri["ime"]) {
            rezultat &&= student["ime"] == parametri["ime"];
          }
          return rezultat;
        });
      })
    );
  }
}
