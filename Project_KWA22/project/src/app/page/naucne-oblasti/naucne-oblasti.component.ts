import { Component, OnInit } from '@angular/core';
import { NaucnaOblast } from '../../model/naucna-oblast';
import { NaucneOblastiService } from '../../service/naucne-oblasti.service';

@Component({
  selector: 'app-naucne-oblasti',
  templateUrl: './naucne-oblasti.component.html',
  styleUrls: ['./naucne-oblasti.component.css']
})
export class NaucneOblastiComponent implements OnInit {
  title="Primer Naucne oblasti"
  
  //Naucna oblast
  naucneOblasti : NaucnaOblast[]=[];
  itemUpdate : NaucnaOblast | null = null;


  constructor(private service : NaucneOblastiService) {
    service.getAll().subscribe(naucneOblasti => {
      this.naucneOblasti = naucneOblasti;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.naucneOblasti = value;
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

  create(naucnaOblast: NaucnaOblast) {
    this.service.create(naucnaOblast).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(naucnaOblast: NaucnaOblast) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, naucnaOblast).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(naucnaOblast: any) {
    this.itemUpdate = { ...naucnaOblast };
  }


}