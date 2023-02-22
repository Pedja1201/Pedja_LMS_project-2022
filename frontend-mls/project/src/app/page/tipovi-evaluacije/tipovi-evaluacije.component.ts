import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TipEvaluacije, TipEvaluacijePage } from 'src/app/model/tip-evaluacije';
import { TipoviEvaluacijeService } from 'src/app/service/tipovi-evaluacije.service';

@Component({
  selector: 'app-tipovi-evaluacije',
  templateUrl: './tipovi-evaluacije.component.html',
  styleUrls: ['./tipovi-evaluacije.component.css']
})
export class TipoviEvaluacijeComponent implements OnInit {
  title="Tip Evaluacije"
  prikaz = false;

  
  tipoviEvaluacije : TipEvaluacije[] = [];
  tipoviUpdate: TipEvaluacije | null = null;

  constructor(private service : TipoviEvaluacijeService, public snackBar:MatSnackBar) {
    service.getAll().subscribe((tipoviEvaluacije : TipEvaluacijePage<TipEvaluacije>) => {
      this.tipoviEvaluacije = tipoviEvaluacije.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.tipoviEvaluacije = value.content;
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

  create(tipEvaluacije: TipEvaluacije) {
    this.service.create(tipEvaluacije).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(tipEvaluacije: TipEvaluacije) {
    if(this.tipoviUpdate && this.tipoviUpdate.id) {
      this.service.update(this.tipoviUpdate.id, tipEvaluacije).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(tipEvaluacije: any) {
    this.tipoviUpdate = { ...tipEvaluacije };
    this.prikaz = true;
  }


}
