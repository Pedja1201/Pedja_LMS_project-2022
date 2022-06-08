import { Component, OnInit } from '@angular/core';
import { Zvanje } from '../../model/zvanje';
import { ZvanjaService } from '../../service/zvanja.service';

@Component({
  selector: 'app-zvanja',
  templateUrl: './zvanja.component.html',
  styleUrls: ['./zvanja.component.css']
})
export class ZvanjaComponent implements OnInit {
  title="Primer Zvanja";
  prikaz = false;
  
  //Zvanje
  zvanja : Zvanje[]=[];
  zvanjeUpdate : Zvanje | null = null;


  constructor(private service : ZvanjaService) {
    service.getAll().subscribe(zvanja => {
      this.zvanja = zvanja;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.zvanja = value;
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

  create(zvanje: Zvanje) {
    this.service.create(zvanje).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(zvanje: Zvanje) {
    if(this.zvanjeUpdate && this.zvanjeUpdate.id) {
      this.service.update(this.zvanjeUpdate.id, zvanje).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(zvanje: any) {
    this.zvanjeUpdate = { ...zvanje };
    this.prikaz = true;
  }


}
