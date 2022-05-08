import { Component, OnInit } from '@angular/core';

import { Ishod } from '../../model/ishod';
import { IshodiService } from '../../service/ishodi.service';
import { PredmetiService } from '../../service/predmeti.service';

@Component({
  selector: 'app-ishodi',
  templateUrl: './ishodi.component.html',
  styleUrls: ['./ishodi.component.css']
})
export class IshodiComponent implements OnInit {
  title="Primer Ishoda"
  
  //Ishod
  ishodi : Ishod[]=[];
  ishodUpdate : Ishod | null = null;


  constructor(private service : IshodiService, private predmetiService : PredmetiService) {
    service.getAll().subscribe(ishodi => {
      this.ishodi = ishodi;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.ishodi = value;
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

  create(ishod: Ishod) {
    this.service.create(ishod).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(ishod: Ishod) {
    if(this.ishodUpdate && this.ishodUpdate.id) {
      this.service.update(this.ishodUpdate.id, ishod).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(ishod: any) {
    this.ishodUpdate = { ...ishod };
  }

}