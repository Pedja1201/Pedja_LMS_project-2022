import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Drzava } from 'src/app/model/drzava';
import { NastavniMaterijal, NastavniMaterijalPage } from 'src/app/model/nastavni-materijal';
import { LoginService } from 'src/app/service/login.service';
import { NastavniMaterijaliService } from 'src/app/service/nastavni-materijali.service';

@Component({
  selector: 'app-nastavni-materijal',
  templateUrl: './nastavni-materijal.component.html',
  styleUrls: ['./nastavni-materijal.component.css']
})
export class NastavniMaterijalComponent implements OnInit {
  title="Nastavni Materijal"
  prikaz = false;

  //Nastavni Materijal
  nastavniMaterijali : NastavniMaterijal[] = [];
  materijalUpdate: NastavniMaterijal | null = null;

  constructor(private service : NastavniMaterijaliService, public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((nastavniMaterijali : NastavniMaterijalPage<NastavniMaterijal>) => {
      this.nastavniMaterijali = nastavniMaterijali.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.nastavniMaterijali = value.content;
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

  create(nastavniMaterijal: NastavniMaterijal) {
    this.service.create(nastavniMaterijal).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(nastavniMaterijal: NastavniMaterijal) {
    if(this.materijalUpdate && this.materijalUpdate.id) {
      this.service.update(this.materijalUpdate.id, nastavniMaterijal).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(nastavniMaterijal: any) {
    this.materijalUpdate = { ...nastavniMaterijal };
    this.prikaz = true;
  }

}
