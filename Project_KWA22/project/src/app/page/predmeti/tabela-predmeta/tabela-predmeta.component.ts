import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Predmet, PredmetPage } from 'src/app/model/predmet';

@Component({
  selector: 'app-tabela-predmeta',
  templateUrl: './tabela-predmeta.component.html',
  styleUrls: ['./tabela-predmeta.component.css']
})
export class TabelaPredmetaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', 'espb', 'obavezan','brojPredavanja', 'brojVezbi', 'drugiObliciNastave', 'istrazivackiRad', "akcije"];
  dataSource : PredmetPage<Predmet> |undefined;
  title="Tabela Predmeta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();

  constructor(private router : Router) { }

  ngOnInit(): void {
  }

  ukloni(id:number) {
    this.uklanjanje.emit(id);
  }

  izmeni(id:number) {
    this.izmena.emit(id);
  }

  prikaziDetalje(predmet: Predmet) {
    this.router.navigate(["/predmeti", predmet.id]);
  }

}
