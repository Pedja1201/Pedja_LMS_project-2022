import { Component, OnInit } from '@angular/core';
import { Student } from '../../model/student';
import { StudentiService } from '../../service/studenti.service';

@Component({
  selector: 'app-studenti',
  templateUrl: './studenti.component.html',
  styleUrls: ['./studenti.component.css']
})
export class StudentiComponent implements OnInit {
  title="Primer studenata"
  
  //Studenti
  studenti : Student[]=[];
  studentUpdate : Student | null = null;
  parametri : any = {}; //Za pretragu


  constructor(private studentiService : StudentiService) {
    studentiService.getAll().subscribe(studenti => {
      this.studenti = studenti;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.studentiService.getAll().subscribe((value) => {
      this.studenti = value;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.studentiService.delete(id).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  create(student: Student) {
    this.studentiService.create(student).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(student: Student) {
    if(this.studentUpdate && this.studentUpdate.id) {
      this.studentiService.update(this.studentUpdate.id, student).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(student: any) {
    this.studentUpdate = { ...student };
  }

  // Pretraga
  search(parametri : any) {
    if(parametri === undefined) {
      parametri = this.parametri;
    } else {
      this.parametri = parametri;
    }
    this.studentiService.pretrazi(parametri).subscribe((studenti : Student[]) => {
      this.studenti = studenti;
    });
  }

}