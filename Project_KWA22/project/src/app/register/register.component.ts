import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Admin } from '../model/admin';
import { NastavnikPage, Nastavnik } from '../model/nastavnik';
import { Student, StudentPage } from '../model/student';
import { LoginService } from '../service/login.service';
import { NastavniciService } from '../service/nastavnici.service';
import { StudentiService } from '../service/studenti.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  title="Registruj se";

  constructor(public loginService : LoginService,  public snackBar:MatSnackBar) {

  }

  ngOnInit(): void {
  }

  createNastavnik(nastavnik: Nastavnik) {
    this.loginService.registerNastavnik(nastavnik).subscribe((value) => {
      console.log(nastavnik)
    }, (error) => {
      console.log(error);
    })
  }
  createStudent(student: Student) {
    this.loginService.registerStudent(student).subscribe((value) => {
      console.log(student)
    }, (error) => {
      console.log(error);
    })
  }

  createAdmin(admin: Admin) {
    this.loginService.registerAdmin(admin).subscribe((value) => {
      console.log(admin)
    }, (error) => {
      console.log(error);
    })
  }

}
