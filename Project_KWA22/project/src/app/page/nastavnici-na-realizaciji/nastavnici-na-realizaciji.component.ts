import { Component, OnInit } from '@angular/core';
import { NastavnikNaRealizaciji } from '../../model/nastavnik-na-realizaciji';
import { NastavniciNaRealizacijiService } from '../../service/nastavnici-na-realizaciji.service';

@Component({
  selector: 'app-nastavnici-na-realizaciji',
  templateUrl: './nastavnici-na-realizaciji.component.html',
  styleUrls: ['./nastavnici-na-realizaciji.component.css']
})
export class NastavniciNaRealizacijiComponent implements OnInit {
  title="Primer Nastavnika na realizaciji"
  
  //Nastavnik na realizaciji
  nastavnici : NastavnikNaRealizaciji[]=[];
  itemUpdate : NastavnikNaRealizaciji | null = null;


  constructor(private service : NastavniciNaRealizacijiService) {
    service.getAll().subscribe(nastavniciNaRealizaciji => {
      this.nastavnici = nastavniciNaRealizaciji;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.nastavnici = value;
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

  create(nastavnik: NastavnikNaRealizaciji) {
    this.service.create(nastavnik).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(nastavnik: NastavnikNaRealizaciji) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, nastavnik).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(nastavnik: any) {
    this.itemUpdate = { ...nastavnik };
  }



}