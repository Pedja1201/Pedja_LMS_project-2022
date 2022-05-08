import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Fakultet } from 'src/app/model/fakultet';
import { Student } from 'src/app/model/student';
import { FakultetiService } from 'src/app/service/fakulteti.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-fakulteta',
  templateUrl: './tabela-fakulteta.component.html',
  styleUrls: ['./tabela-fakulteta.component.css']
})
export class TabelaFakultetaComponent implements OnInit {

  title="Tabela Fakulteta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : FakultetiService, private router : Router) { 
    servis.getAll().subscribe(fakulteti => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = fakulteti;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(fakultet: Fakultet) {
    this.router.navigate(["/fakulteti", fakultet.id]);
  }

}