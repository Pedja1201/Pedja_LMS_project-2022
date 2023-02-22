import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { data } from 'jquery';
import { Nastavnik, NastavnikPage } from '../../model/nastavnik';
import { NastavniciService } from '../../service/nastavnici.service';

@Component({
  selector: 'app-nastavnici',
  templateUrl: './nastavnici.component.html',
  styleUrls: ['./nastavnici.component.css']
})
export class NastavniciComponent implements OnInit {
  title="Primer Nastavnika";
  prikaz = false;
  prikazPretrage = false;
  
  //Nastavnik
  nastavnici : Nastavnik[]=[];
  nastavnikUpdate : Nastavnik | null = null;
  parametri : any = {}; //Za pretragu


  constructor(private service : NastavniciService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((nastavnici : NastavnikPage<Nastavnik>) => {
      this.nastavnici = nastavnici.content;
    })
  }

  export(){
    this.service.exportPdf().subscribe((data) => {
      let downloadURL = window.URL.createObjectURL(data);
      let link = document.createElement('a');
      link.href=downloadURL;
      link.download = 'nastavniciList.pdf';
      link.click()
    })
  }

  open(){
    this.prikazPretrage = true
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

  create(nastavnik: Nastavnik) {
    this.service.create(nastavnik).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(nastavnik: Nastavnik) {
    if(this.nastavnikUpdate && this.nastavnikUpdate.id) {
      this.service.update(this.nastavnikUpdate.id, nastavnik).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(nastavnik: any) {
    this.nastavnikUpdate = { ...nastavnik };
    this.prikaz = true;
  }

  // Pretraga
  search(parametri : any) {
    if(parametri === undefined) {
      parametri = this.parametri;
    } else {
      this.parametri = parametri;
    }
    this.service.pretrazi(parametri).subscribe((nastavnici : Nastavnik[]) => {
      this.nastavnici = nastavnici;
    });
  }


}
