import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TipZvanja, TipZvanjaPage } from '../../model/tip-zvanja';
import { TipoviZvanjaService } from '../../service/tipovi-zvanja.service';

@Component({
  selector: 'app-tipovi-zvanja',
  templateUrl: './tipovi-zvanja.component.html',
  styleUrls: ['./tipovi-zvanja.component.css']
})
export class TipoviZvanjaComponent implements OnInit {
  title="Primer Tipa zvanja";
  prikaz = false;
  
  tipoviZvanja : TipZvanja[] = [];
  itemUpdate: TipZvanja | null = null;

  constructor(private service : TipoviZvanjaService, public snackBar:MatSnackBar) {
    service.getAll().subscribe((tipoviZvanja : TipZvanjaPage<TipZvanja>) => {
      this.tipoviZvanja = tipoviZvanja.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.tipoviZvanja = value.content;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.service.delete(id).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Deleted...', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  create(tipZvanja: TipZvanja) {
    this.service.create(tipZvanja).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(tipZvanja: TipZvanja) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, tipZvanja).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(tipZvanja: any) {
    this.itemUpdate = { ...tipZvanja };
    this.prikaz = true;
  }

}
