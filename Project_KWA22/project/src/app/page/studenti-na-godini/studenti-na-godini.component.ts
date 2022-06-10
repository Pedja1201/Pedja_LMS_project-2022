import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StudentNaGodini } from '../../model/student-na-godini';
import { StudentiNaGodiniService } from '../../service/studenti-na-godini.service';

@Component({
  selector: 'app-studenti-na-godini',
  templateUrl: './studenti-na-godini.component.html',
  styleUrls: ['./studenti-na-godini.component.css']
})
export class StudentiNaGodiniComponent implements OnInit {
  title="Primer Studenta na godini";
  prikaz = false;
  

  studentiNaGodini : StudentNaGodini[]=[];
  itemUpdate : StudentNaGodini | null = null;


  constructor(private service : StudentiNaGodiniService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(studentiNaGodini => {
      this.studentiNaGodini = studentiNaGodini;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.studentiNaGodini = value;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.service.delete(id).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Deleted...', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  create(studentNaGodini: StudentNaGodini) {
    this.service.create(studentNaGodini).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(studentNaGodini: StudentNaGodini) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, studentNaGodini).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(studentNaGodini: any) {
    this.itemUpdate = { ...studentNaGodini };
    this.prikaz = true;
  }

}
