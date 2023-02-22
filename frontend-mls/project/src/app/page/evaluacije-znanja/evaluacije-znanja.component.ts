import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EvaluacijaZnanja, EvaluacijaZnanjaPage } from 'src/app/model/evaluacija-znanja';
import { EvaluacijeZnanjaService } from 'src/app/service/evaluacije-znanja.service';

@Component({
  selector: 'app-evaluacije-znanja',
  templateUrl: './evaluacije-znanja.component.html',
  styleUrls: ['./evaluacije-znanja.component.css']
})
export class EvaluacijeZnanjaComponent implements OnInit {

  title="Evaluacija Znanja"
  prikaz = false;
  
  //Ev.Znanja
  evaluacijeZnanja : EvaluacijaZnanja[] = [];
  znanjeUpdate: EvaluacijaZnanja | null = null;

  constructor(private service : EvaluacijeZnanjaService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((evaluacijeZnanja : EvaluacijaZnanjaPage<EvaluacijaZnanja>)=> {
      this.evaluacijeZnanja = evaluacijeZnanja.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.evaluacijeZnanja = value.content;
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

  create(evaluacijaZnanja: EvaluacijaZnanja) {
    this.service.create(evaluacijaZnanja).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(evaluacijaZnanja: EvaluacijaZnanja) {
    if(this.znanjeUpdate && this.znanjeUpdate.id) {
      this.service.update(this.znanjeUpdate.id, evaluacijaZnanja).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(evaluacijaZnanja: any) {
    this.znanjeUpdate = { ...evaluacijaZnanja };
    this.prikaz = true;
  }

}
