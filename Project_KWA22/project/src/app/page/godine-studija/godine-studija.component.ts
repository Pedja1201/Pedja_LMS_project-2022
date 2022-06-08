import { Component, OnInit } from '@angular/core';
import { GodinaStudija } from '../../model/godina-studija';
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


  constructor(private service : GodineStudijaService, private predmetiService : PredmetiService) {
    service.getAll().subscribe(godineStudija => {
      this.godineStudija = godineStudija;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.godineStudija = value;
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

  create(godinaStudija: GodinaStudija) {
    this.service.create(godinaStudija).subscribe((value) => {
      this.getAll();
    }, (error) => {
      console.log(error);
    })
  }

  update(godinaStudija: GodinaStudija) {
    if(this.godinaStudijaUpdate && this.godinaStudijaUpdate.id) {
      this.service.update(this.godinaStudijaUpdate.id, godinaStudija).subscribe((value) => {
        this.getAll();
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
