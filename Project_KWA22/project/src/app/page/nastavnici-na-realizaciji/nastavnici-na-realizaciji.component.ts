import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NastavnikNaRealizaciji, NastavnikNaRealizacijiPage } from '../../model/nastavnik-na-realizaciji';
import { NastavniciNaRealizacijiService } from '../../service/nastavnici-na-realizaciji.service';

@Component({
  selector: 'app-nastavnici-na-realizaciji',
  templateUrl: './nastavnici-na-realizaciji.component.html',
  styleUrls: ['./nastavnici-na-realizaciji.component.css']
})
export class NastavniciNaRealizacijiComponent implements OnInit {
  title="Primer Nastavnika na realizaciji";
  prikaz = false;

  
  //Nastavnik na realizaciji
  nastavnici : NastavnikNaRealizaciji[]=[];
  itemUpdate : NastavnikNaRealizaciji | null = null;


  constructor(private service : NastavniciNaRealizacijiService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((nastavniciNaRealizaciji : NastavnikNaRealizacijiPage<NastavnikNaRealizaciji>) => {
      this.nastavnici = nastavniciNaRealizaciji.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.nastavnici = value.content;
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

  create(nastavnik: NastavnikNaRealizaciji) {
    this.service.create(nastavnik).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(nastavnik: NastavnikNaRealizaciji) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, nastavnik).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(nastavnik: any) {
    this.itemUpdate = { ...nastavnik };
    this.prikaz = true;
  }



}
