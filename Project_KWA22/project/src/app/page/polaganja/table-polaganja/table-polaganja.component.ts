import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Adresa } from 'src/app/model/adresa';
import { Polaganje } from 'src/app/model/polaganje';
import { AdreseService } from 'src/app/service/adrese.service';
import { PolaganjaService } from 'src/app/service/polaganja.service';

@Component({
  selector: 'app-table-polaganja',
  templateUrl: './table-polaganja.component.html',
  styleUrls: ['./table-polaganja.component.css']
})
export class TablePolaganjaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'bodovi', 'napomena', 'evaluacijaZnanja', 'studentNaGodini', "akcije"];
  dataSource : Adresa[]=[];
  title="Tabela Polaganja";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private service : PolaganjaService, private router : Router) { 
    service.getAll().subscribe(polaganja => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = polaganja;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(polaganje: Polaganje) {
    this.router.navigate(["/polaganja", polaganje.id]);
  }

}
