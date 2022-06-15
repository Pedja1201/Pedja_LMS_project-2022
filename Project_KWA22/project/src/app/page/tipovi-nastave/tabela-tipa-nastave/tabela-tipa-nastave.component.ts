import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TipNastave, TipNastavePage } from 'src/app/model/tip-nastave';

@Component({
  selector: 'app-tabela-tipa-nastave',
  templateUrl: './tabela-tipa-nastave.component.html',
  styleUrls: ['./tabela-tipa-nastave.component.css']
})
export class TabelaTipaNastaveComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : TipNastavePage<TipNastave> | undefined;
  title="Tabela Tipa nastave";

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

  prikaziDetalje(tipNastave: TipNastave) {
    this.router.navigate(["/tipoviNastave", tipNastave.id]);
  }

}
