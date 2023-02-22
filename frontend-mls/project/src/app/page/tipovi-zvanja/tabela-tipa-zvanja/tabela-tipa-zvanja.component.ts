import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TipZvanja, TipZvanjaPage } from 'src/app/model/tip-zvanja';

@Component({
  selector: 'app-tabela-tipa-zvanja',
  templateUrl: './tabela-tipa-zvanja.component.html',
  styleUrls: ['./tabela-tipa-zvanja.component.css']
})
export class TabelaTipaZvanjaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : TipZvanjaPage<TipZvanja> | undefined;
  title="Tabela Tipa zvanja";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje: EventEmitter<any> = new EventEmitter<any>();

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

  prikaziDetalje(tipZvanja: TipZvanja) {
    this.router.navigate(["/tipoviZvanja", tipZvanja.id]);
  }

}
