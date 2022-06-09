import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StudijskiProgram } from '../../model/studijski-program';
import { StudijskiProgramiService } from '../../service/studijski-programi.service';

@Component({
  selector: 'app-studijski-programi',
  templateUrl: './studijski-programi.component.html',
  styleUrls: ['./studijski-programi.component.css']
})
export class StudijskiProgramiComponent implements OnInit {
  title="Primer Studijskih programa";
  prikaz = false;
  
  
  studijskiProgrami : StudijskiProgram[]=[];
  itemUpdate : StudijskiProgram | null = null;


  constructor(private service : StudijskiProgramiService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(studijskiProgrami => {
      this.studijskiProgrami = studijskiProgrami;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.studijskiProgrami = value;
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

  create(studijskiProgram: StudijskiProgram) {
    this.service.create(studijskiProgram).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(studijskiProgram: StudijskiProgram) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, studijskiProgram).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(studijskiProgram: any) {
    this.itemUpdate = { ...studijskiProgram };
    this.prikaz = true;
  }

}
