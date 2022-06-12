import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NastavniMaterijal } from 'src/app/model/nastavni-materijal';

@Component({
  selector: 'app-table-nastavni-materijal',
  templateUrl: './table-nastavni-materijal.component.html',
  styleUrls: ['./table-nastavni-materijal.component.css']
})
export class TableNastavniMaterijalComponent implements OnInit {
  displayedColumns: string[] = ['id', 'autor','godinaIzdavanja', 'naziv', "akcije"];
  dataSource : NastavniMaterijal[]=[];
  title="Tabela Nastavni Materijal";

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

  prikaziDetalje(nastavniMaterijal: NastavniMaterijal) {
    this.router.navigate(["/nastavniMaterijali", nastavniMaterijal.id]);
  }

}
