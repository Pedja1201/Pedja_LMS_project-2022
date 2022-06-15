import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { GodinaStudija, GodinaStudijaPage } from '../../model/godina-studija';
import { GodineStudijaService } from '../../service/godine-studija.service';
import { PredmetiService } from '../../service/predmeti.service';

@Component({
  selector: 'app-godine-studija',
  templateUrl: './godine-studija.component.html',
  styleUrls: ['./godine-studija.component.css']
})
export class GodineStudijaComponent implements OnInit {
  title="Primer Godine studija";
  prikaz = false;
  
  //Godine studija
  godineStudija : GodinaStudija[]=[];
  godinaStudijaUpdate : GodinaStudija | null = null;


  constructor(private service : GodineStudijaService, private predmetiService : PredmetiService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((godineStudija : GodinaStudijaPage<GodinaStudija>) => {
      this.godineStudija = godineStudija.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.godineStudija = value.content;
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

  create(godinaStudija: GodinaStudija) {
    this.service.create(godinaStudija).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(godinaStudija: GodinaStudija) {
    if(this.godinaStudijaUpdate && this.godinaStudijaUpdate.id) {
      this.service.update(this.godinaStudijaUpdate.id, godinaStudija).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(godinaStudija: any) {
    this.godinaStudijaUpdate = { ...godinaStudija };
    this.prikaz = true;
  }

}
