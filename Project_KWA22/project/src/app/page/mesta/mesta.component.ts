import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { Mesto, MestoPage } from '../../model/mesto';
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


  constructor(private service : MestaService, private drazveService : DrzaveService,  public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((mesta : MestoPage<Mesto>) => {
      this.mesta = mesta.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.mesta = value.content;
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

  create(mesto: Mesto) {
    this.service.create(mesto).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(mesto: Mesto) {
    if(this.mestoUpdate && this.mestoUpdate.id) {
      this.service.update(this.mestoUpdate.id, mesto).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
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
