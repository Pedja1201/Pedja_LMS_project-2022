import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Drzava } from 'src/app/model/drzava';

@Component({
  selector: 'app-tabela-drzave',
  templateUrl: './tabela-drzave.component.html',
  styleUrls: ['./tabela-drzave.component.css']
})
export class TabelaDrzaveComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : Drzava[]=[];
  title="Tabela Drzave";

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

  prikaziDetalje(drzava: Drzava) {
    this.router.navigate(["/drzave", drzava.id]);
  }

}
