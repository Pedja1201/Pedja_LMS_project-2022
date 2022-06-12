import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { IshodNastave } from 'src/app/model/ishod-nastave';

import { AdreseService } from 'src/app/service/adrese.service';
import { IshodiNastaveService } from 'src/app/service/ishodi-nastave.service';

@Component({
  selector: 'app-table-ishodi-nastave',
  templateUrl: './table-ishodi-nastave.component.html',
  styleUrls: ['./table-ishodi-nastave.component.css']
})
export class TableIshodiNastaveComponent implements OnInit {
  displayedColumns: string[] = ['id', 'opis', 'nastavniMaterijal', "akcije"];
  dataSource : IshodNastave[]=[];
  title="Tabela Ishoda Nastave";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private service : IshodiNastaveService, private router : Router) { 
    service.getAll().subscribe(ishodiNastave => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = ishodiNastave;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(ishodNastave: IshodNastave) {
    this.router.navigate(["/ishodiNastave", ishodNastave.id]);
  }

}
