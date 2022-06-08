import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Ishod } from 'src/app/model/ishod';
import { IshodiService } from 'src/app/service/ishodi.service';

@Component({
  selector: 'app-tabela-ishoda',
  templateUrl: './tabela-ishoda.component.html',
  styleUrls: ['./tabela-ishoda.component.css']
})
export class TabelaIshodaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'opis', 'predmet', "akcije"];
  dataSource : Ishod[]=[];
  title="Tabela Ishoda";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : IshodiService, private router : Router) { 
    servis.getAll().subscribe(ishodi => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = ishodi;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(ishod: Ishod) {
    this.router.navigate(["/ishodi", ishod.id]);
  }

}
