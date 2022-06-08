import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Adresa } from '../../model/adresa';
import { AdreseService } from '../../service/adrese.service';

@Component({
  selector: 'app-adrese',
  templateUrl: './adrese.component.html',
  styleUrls: ['./adrese.component.css']
})
export class AdreseComponent implements OnInit {
  title="Primer Adrese";
  prikaz = false;
  
  //Adresa
  adrese : Adresa[] = [];
  adresaUpdate: Adresa | null = null;

  constructor(private service : AdreseService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(adrese => {
      this.adrese = adrese;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.adrese = value;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.service.delete(id).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  create(adresa: Adresa) {
    this.service.create(adresa).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(adresa: Adresa) {
    if(this.adresaUpdate && this.adresaUpdate.id) {
      this.service.update(this.adresaUpdate.id, adresa).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(adresa: any) {
    this.adresaUpdate = { ...adresa };
    this.prikaz = true;
  }

}
