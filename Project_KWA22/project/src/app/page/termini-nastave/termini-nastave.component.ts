import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TerminNastave } from 'src/app/model/termin-nastave';
import { TerminiNastaveService } from 'src/app/service/termini-nastave.service';

@Component({
  selector: 'app-termini-nastave',
  templateUrl: './termini-nastave.component.html',
  styleUrls: ['./termini-nastave.component.css']
})
export class TerminiNastaveComponent implements OnInit {
  title="Termini Nastave"
  prikaz = false;
  
  //TerminNastave
  terminiNastave : TerminNastave[] = [];
  terminUpdate: TerminNastave | null = null;

  constructor(private service : TerminiNastaveService,  public snackBar:MatSnackBar) {
    service.getAll().subscribe(terminiNastave => {
      this.terminiNastave = terminiNastave;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.terminiNastave = value;
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

  create(terminNastave: TerminNastave) {
    this.service.create(terminNastave).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(terminNastave: TerminNastave) {
    if(this.terminUpdate && this.terminUpdate.id) {
      this.service.update(this.terminUpdate.id, terminNastave).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 });
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(terminNastave: any) {
    this.terminUpdate = { ...terminNastave };
    this.prikaz = true;
  }

}
