import { Component, OnInit } from '@angular/core';
import { Drzava } from '../../model/drzava';
import { DrzaveService } from '../../service/drzave.service';

@Component({
  selector: 'app-drzave',
  templateUrl: './drzave.component.html',
  styleUrls: ['./drzave.component.css']
})
export class DrzaveComponent implements OnInit {
  title="Primer Drzave"
  
  //Drzava
  drzave : Drzava[] = [];
  drzavaUpdate: Drzava | null = null;

  constructor(private service : DrzaveService) {
    service.getAll().subscribe(drzave => {
      this.drzave = drzave;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.drzave = value;
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

  create(drzava: Drzava) {
    this.service.create(drzava).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(drzava: Drzava) {
    if(this.drzavaUpdate && this.drzavaUpdate.id) {
      this.service.update(this.drzavaUpdate.id, drzava).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(drzava: any) {
    this.drzavaUpdate = { ...drzava };
  }

}