import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IshodNastave, IshodNastavePage } from 'src/app/model/ishod-nastave';
import { IshodiNastaveService } from 'src/app/service/ishodi-nastave.service';

@Component({
  selector: 'app-ishodi-nastave',
  templateUrl: './ishodi-nastave.component.html',
  styleUrls: ['./ishodi-nastave.component.css']
})
export class IshodiNastaveComponent implements OnInit {
  title="Ishod Nastave"
  prikaz = false;
  
  //IshodNastave
  ishodiNastave : IshodNastave[] = [];
  ishodUpdate: IshodNastave | null = null;

  constructor(private service : IshodiNastaveService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((ishodiNastave : IshodNastavePage<IshodNastave>) => {
      this.ishodiNastave = ishodiNastave.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.ishodiNastave = value.content;
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

  create(ishodNastave: IshodNastave) {
    this.service.create(ishodNastave).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(ishodNastave: IshodNastave) {
    if(this.ishodUpdate && this.ishodUpdate.id) {
      this.service.update(this.ishodUpdate.id, ishodNastave).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(ishodNastave: any) {
    this.ishodUpdate = { ...ishodNastave };
    this.prikaz = true;
  }

}
