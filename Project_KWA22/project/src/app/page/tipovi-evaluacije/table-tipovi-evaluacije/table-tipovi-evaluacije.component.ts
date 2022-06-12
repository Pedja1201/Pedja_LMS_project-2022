import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Drzava } from 'src/app/model/drzava';
import { TipEvaluacije } from 'src/app/model/tip-evaluacije';

@Component({
  selector: 'app-table-tipovi-evaluacije',
  templateUrl: './table-tipovi-evaluacije.component.html',
  styleUrls: ['./table-tipovi-evaluacije.component.css']
})
export class TableTipoviEvaluacijeComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : TipEvaluacije[]=[];
  title="Tabela Tipova Evaluacije";

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

  prikaziDetalje(tipEvaluacije: TipEvaluacije) {
    this.router.navigate(["/tipoviEvaluacije", tipEvaluacije.id]);
  }

}
