import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TipNastave } from '../../model/tip-nastave';
import { TipoviNastaveService } from '../../service/tipovi-nastave.service';

@Component({
  selector: 'app-tipovi-nastave',
  templateUrl: './tipovi-nastave.component.html',
  styleUrls: ['./tipovi-nastave.component.css']
})
export class TipoviNastaveComponent implements OnInit {
  title="Primer Tip nastave";
  prikaz = false;
  
  tipoviNastave : TipNastave[] = [];
  itemUpdate: TipNastave | null = null;

  constructor(private service : TipoviNastaveService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(tipoviNastave => {
      this.tipoviNastave = tipoviNastave;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.tipoviNastave = value;
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

  create(tipNastave: TipNastave) {
    this.service.create(tipNastave).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(tipNastave: TipNastave) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, tipNastave).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(tipNastave: any) {
    this.itemUpdate = { ...tipNastave };
    this.prikaz = true;
  }

}
