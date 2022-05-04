import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { MestaService } from 'src/app/service/mesta.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-mesta',
  templateUrl: './tabela-mesta.component.html',
  styleUrls: ['./tabela-mesta.component.css']
})
export class TabelaMestaComponent implements OnInit {
  title="Tabela Mesta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : MestaService, private router : Router) { 
    servis.getAll().subscribe(mesta => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = mesta;                            //Rutiranja posebne tabele komponenete       
    });
  }

  ngOnInit(): void {
  }

  ukloni(id:number) {
    this.uklanjanje.emit(id);
  }

  izmeni(id:number) {
    this.izmena.emit(id);
  }

  prikaziDetalje(mesto: Student) {
    this.router.navigate(["/mesta", mesto.id]);
  }

}
