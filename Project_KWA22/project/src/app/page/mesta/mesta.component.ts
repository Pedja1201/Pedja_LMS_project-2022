import { Component, OnInit } from '@angular/core';
import { Mesto } from '../../model/mesto';
import { DrzaveService } from '../../service/drzave.service';
import { MestaService } from '../../service/mesta.service';

@Component({
  selector: 'app-mesta',
  templateUrl: './mesta.component.html',
  styleUrls: ['./mesta.component.css']
})
export class MestaComponent implements OnInit {
  title="Primer Mesto";
  prikaz = false;
  
  //Mesto
  mesta : Mesto[]=[];
  mestoUpdate : Mesto | null = null;


  constructor(private service : MestaService, private drazveService : DrzaveService) {
    service.getAll().subscribe(mesta => {
      this.mesta = mesta;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.mesta = value;
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

  create(mesto: Mesto) {
    this.service.create(mesto).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(mesto: Mesto) {
    if(this.mestoUpdate && this.mestoUpdate.id) {
      this.service.update(this.mestoUpdate.id, mesto).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(mesto: any) {
    this.mestoUpdate = { ...mesto };
    this.prikaz = true;
  }

}
