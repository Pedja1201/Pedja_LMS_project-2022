import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RealizacijaPredmeta } from '../../model/realizacija-predmeta';
import { RealizacijePredmetaService } from '../../service/realizacije-predmeta.service';

@Component({
  selector: 'app-realizacije-predmeta',
  templateUrl: './realizacije-predmeta.component.html',
  styleUrls: ['./realizacije-predmeta.component.css']
})
export class RealizacijePredmetaComponent implements OnInit {
  title="Primer Realizacije predmeta";
  prikaz = false;
  
  realizacijePredmeta : RealizacijaPredmeta[]=[];
  itemUpdate : RealizacijaPredmeta | null = null;


  constructor(private service : RealizacijePredmetaService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(realizacijePredmeta => {
      this.realizacijePredmeta = realizacijePredmeta;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.realizacijePredmeta = value;
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

  create(realizacijaPredmeta: RealizacijaPredmeta) {
    this.service.create(realizacijaPredmeta).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(realizacijaPredmeta: RealizacijaPredmeta) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, realizacijaPredmeta).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(realizacijaPredmeta: any) {
    this.itemUpdate = { ...realizacijaPredmeta };
    this.prikaz = true;
  }

}
