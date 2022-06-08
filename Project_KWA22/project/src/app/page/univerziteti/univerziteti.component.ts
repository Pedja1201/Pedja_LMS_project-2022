import { Component, OnInit } from '@angular/core';
import { Univerzitet } from '../../model/univerzitet';
import { UniverzitetiService } from '../../service/univerziteti.service';

@Component({
  selector: 'app-univerziteti',
  templateUrl: './univerziteti.component.html',
  styleUrls: ['./univerziteti.component.css']
})
export class UniverzitetiComponent implements OnInit {
  title="Primer Univerziteta";
  prikaz = false;
  
  //Univerzitet
  univerziteti : Univerzitet[]=[];
  univerzitetUpdate : Univerzitet | null = null;


  constructor(private service : UniverzitetiService) {
    service.getAll().subscribe(univerziteti => {
      this.univerziteti = univerziteti;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.univerziteti = value;
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

  create(univerzitet: Univerzitet) {
    this.service.create(univerzitet).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(univerzitet: Univerzitet) {
    if(this.univerzitetUpdate && this.univerzitetUpdate.id) {
      this.service.update(this.univerzitetUpdate.id, univerzitet).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(univerzitet: any) {
    this.univerzitetUpdate = { ...univerzitet };
    this.prikaz = true;
  }


}
