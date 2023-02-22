import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NastavniMaterijal, NastavniMaterijalPage } from 'src/app/model/nastavni-materijal';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-table-nastavni-materijal',
  templateUrl: './table-nastavni-materijal.component.html',
  styleUrls: ['./table-nastavni-materijal.component.css']
})
export class TableNastavniMaterijalComponent implements OnInit {
  displayedColumns: string[] = ['id', 'autor','godinaIzdavanja', 'naziv', "akcije"];
  dataSource : NastavniMaterijalPage<NastavniMaterijal> | undefined;
  title="Tabela Nastavni Materijal";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();

  constructor(private router : Router, public loginService : LoginService) { }

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
