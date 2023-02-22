import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';

import { Ishod, IshodPage } from '../../model/ishod';
import { IshodiService } from '../../service/ishodi.service';
import { PredmetiService } from '../../service/predmeti.service';

@Component({
  selector: 'app-ishodi',
  templateUrl: './ishodi.component.html',
  styleUrls: ['./ishodi.component.css']
})
export class IshodiComponent implements OnInit {
  title="Primer Ishoda";
  prikaz = false;
  
  //Ishod
  ishodi : Ishod[]=[];
  ishodUpdate : Ishod | null = null;


  constructor(private service : IshodiService, private predmetiService : PredmetiService,  public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((ishodi : IshodPage<Ishod>) => {
      this.ishodi = ishodi.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.ishodi = value.content;
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

  create(ishod: Ishod) {
    this.service.create(ishod).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(ishod: Ishod) {
    if(this.ishodUpdate && this.ishodUpdate.id) {
      this.service.update(this.ishodUpdate.id, ishod).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(ishod: any) {
    this.ishodUpdate = { ...ishod };
    this.prikaz = true;
  }

}
