import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Drzava, DrzavaPage } from 'src/app/model/drzava';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-tabela-drzave',
  templateUrl: './tabela-drzave.component.html',
  styleUrls: ['./tabela-drzave.component.css']
})
export class TabelaDrzaveComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : DrzavaPage<Drzava> | undefined;
  title="Tabela Drzave";

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

  prikaziDetalje(drzava: Drzava) {
    this.router.navigate(["/drzave", drzava.id]);
  }

}
