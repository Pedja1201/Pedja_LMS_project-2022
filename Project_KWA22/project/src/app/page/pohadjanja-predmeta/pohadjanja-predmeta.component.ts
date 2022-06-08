import { Component, OnInit } from '@angular/core';
import { PohadjanjePredmeta } from '../../model/pohadjanje-predmeta';
import { PohadjanjaPredmetaService } from '../../service/pohadjanja-predmeta.service';

@Component({
  selector: 'app-pohadjanja-predmeta',
  templateUrl: './pohadjanja-predmeta.component.html',
  styleUrls: ['./pohadjanja-predmeta.component.css']
})
export class PohadjanjaPredmetaComponent implements OnInit {
  title="Primer pohadjanih predmeta";
  prikaz = false;
  

  pohadjanjaPredmeta : PohadjanjePredmeta[]=[];
  itemUpdate : PohadjanjePredmeta | null = null;


  constructor(private service : PohadjanjaPredmetaService) {
    service.getAll().subscribe(pohadjanjaPredmeta => {
      this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.pohadjanjaPredmeta = value;
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

  create(pohadjanjePredmeta: PohadjanjePredmeta) {
    this.service.create(pohadjanjePredmeta).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(pohadjanjePredmeta: PohadjanjePredmeta) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, pohadjanjePredmeta).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(pohadjanjePredmeta: any) {
    this.itemUpdate = { ...pohadjanjePredmeta };
    this.prikaz = true;
  }

}
