import { Component, OnInit } from '@angular/core';
import { Fakultet } from '../../model/fakultet';
import { AdreseService } from '../../service/adrese.service';
import { FakultetiService } from '../../service/fakulteti.service';
import { NastavniciService } from '../../service/nastavnici.service';
import { UniverzitetiService } from '../../service/univerziteti.service';

@Component({
  selector: 'app-fakulteti',
  templateUrl: './fakulteti.component.html',
  styleUrls: ['./fakulteti.component.css']
})
export class FakultetiComponent implements OnInit {

  title="Primer Fakulteta"
  
  //Fakulteti
  fakulteti : Fakultet[]=[];
  fakultetUpdate : Fakultet | null = null;


  constructor(private service : FakultetiService, private univerzitetiService : UniverzitetiService, private adreseService : AdreseService, private nastavniciService : NastavniciService) {
    service.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
    })
  }


  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.fakulteti = value;
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

  create(fakultet: Fakultet) {
    this.service.create(fakultet).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(fakultet: Fakultet) {
    if(this.fakultetUpdate && this.fakultetUpdate.id) {
      this.service.update(this.fakultetUpdate.id, fakultet).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(fakultet: any) {
    this.fakultetUpdate = { ...fakultet };
  }

}
