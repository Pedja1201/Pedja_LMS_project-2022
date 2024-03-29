import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { PohadjanjePredmeta, PohadjanjePredmetaPage } from '../../model/pohadjanje-predmeta';
import { PohadjanjaPredmetaService } from '../../service/pohadjanja-predmeta.service';

@Component({
  selector: 'app-pohadjanja-predmeta',
  templateUrl: './pohadjanja-predmeta.component.html',
  styleUrls: ['./pohadjanja-predmeta.component.css']
})
export class PohadjanjaPredmetaComponent implements OnInit {
  title="Primer pohadjanih predmeta";
  prikaz = false;
  

  pohadjanjaPredmeta : PohadjanjePredmeta[]=[];
  itemUpdate : PohadjanjePredmeta | null = null;


  constructor(private service : PohadjanjaPredmetaService,  public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((pohadjanjaPredmeta : PohadjanjePredmetaPage<PohadjanjePredmeta>) => {
      this.pohadjanjaPredmeta = pohadjanjaPredmeta.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.pohadjanjaPredmeta = value.content;
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

  create(pohadjanjePredmeta: PohadjanjePredmeta) {
    this.service.create(pohadjanjePredmeta).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(pohadjanjePredmeta: PohadjanjePredmeta) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, pohadjanjePredmeta).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(pohadjanjePredmeta: any) {
    this.itemUpdate = { ...pohadjanjePredmeta };
    this.prikaz = true;
  }

}
