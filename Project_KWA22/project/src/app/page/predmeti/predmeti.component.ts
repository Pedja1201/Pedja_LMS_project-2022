import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Predmet } from '../../model/predmet';
import { PredmetiService } from '../../service/predmeti.service';

@Component({
  selector: 'app-predmeti',
  templateUrl: './predmeti.component.html',
  styleUrls: ['./predmeti.component.css']
})
export class PredmetiComponent implements OnInit {
  title="Primer Predmeta";
  prikaz = false;
  
  predmeti : Predmet[] = [];
  predmetUpdate: Predmet | null = null;

  constructor(private service : PredmetiService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe(predmeti => {
      this.predmeti = predmeti;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.predmeti = value;
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

  create(predmet: Predmet) {
    this.service.create(predmet).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(predmet: Predmet) {
    if(this.predmetUpdate && this.predmetUpdate.id) {
      this.service.update(this.predmetUpdate.id, predmet).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(predmet: any) {
    this.predmetUpdate = { ...predmet };
    this.prikaz = true;
  }

}
