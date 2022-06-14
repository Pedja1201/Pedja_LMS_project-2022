import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Adresa } from 'src/app/model/adresa';
import { Mesto } from 'src/app/model/mesto';
import { AdreseService } from 'src/app/service/adrese.service';

@Component({
  selector: 'app-tabela-adrese',
  templateUrl: './tabela-adrese.component.html',
  styleUrls: ['./tabela-adrese.component.css']
})
export class TabelaAdreseComponent implements OnInit {
  displayedColumns: string[] = ['id', 'ulica', 'broj', 'mesto', "akcije"];
  dataSource : Adresa[]=[]; //U dataSource je problem prilikom dobavljanja za backend
  // dataSource = new MatTableDataSource();

  title="Tabela Adresa";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private service : AdreseService, private router : Router) { 
    service.getAll().subscribe(adrese => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = adrese;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(adresa: Adresa) {
    this.router.navigate(["/adrese", adresa.id]);
  }

}
