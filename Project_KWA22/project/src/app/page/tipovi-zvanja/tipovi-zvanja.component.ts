import { Component, OnInit } from '@angular/core';
import { TipZvanja } from '../../model/tip-zvanja';
import { TipoviZvanjaService } from '../../service/tipovi-zvanja.service';

@Component({
  selector: 'app-tipovi-zvanja',
  templateUrl: './tipovi-zvanja.component.html',
  styleUrls: ['./tipovi-zvanja.component.css']
})
export class TipoviZvanjaComponent implements OnInit {
  title="Primer Tipa zvanja"
  
  tipoviZvanja : TipZvanja[] = [];
  itemUpdate: TipZvanja | null = null;

  constructor(private service : TipoviZvanjaService) {
    service.getAll().subscribe(tipoviZvanja => {
      this.tipoviZvanja = tipoviZvanja;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.tipoviZvanja = value;
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

  create(tipZvanja: TipZvanja) {
    this.service.create(tipZvanja).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(tipZvanja: TipZvanja) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, tipZvanja).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(tipZvanja: any) {
    this.itemUpdate = { ...tipZvanja };
  }

}
