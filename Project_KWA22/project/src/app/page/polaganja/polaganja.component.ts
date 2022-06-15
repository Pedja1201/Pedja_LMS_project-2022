import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Polaganje, PolaganjePage } from 'src/app/model/polaganje';
import { PolaganjaService } from 'src/app/service/polaganja.service';

@Component({
  selector: 'app-polaganja',
  templateUrl: './polaganja.component.html',
  styleUrls: ['./polaganja.component.css']
})
export class PolaganjaComponent implements OnInit {

  title="Polaganje"
  prikaz = false;
  
  //Polaganje
  polaganja : Polaganje[] = [];
  polaganjeUpdate: Polaganje | null = null;

  constructor(private service : PolaganjaService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe((polaganja : PolaganjePage<Polaganje>) => {
      this.polaganja = polaganja.content;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.polaganja = value.content;
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

  create(polaganje: Polaganje) {
    this.service.create(polaganje).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(polaganje: Polaganje) {
    if(this.polaganjeUpdate && this.polaganjeUpdate.id) {
      this.service.update(this.polaganjeUpdate.id, polaganje).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(polaganje: any) {
    this.polaganjeUpdate = { ...polaganje };
    this.prikaz = true;
  }
}
