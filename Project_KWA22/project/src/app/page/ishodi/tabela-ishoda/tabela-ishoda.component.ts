import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Ishod, IshodPage } from 'src/app/model/ishod';
import { IshodiService } from 'src/app/service/ishodi.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-tabela-ishoda',
  templateUrl: './tabela-ishoda.component.html',
  styleUrls: ['./tabela-ishoda.component.css']
})
export class TabelaIshodaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'opis', 'predmet', "akcije"];
  dataSource :IshodPage<Ishod> | undefined;
  title="Tabela Ishoda";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : IshodiService, private router : Router, public loginService : LoginService) { 
    servis.getAll().subscribe((ishodi : IshodPage<Ishod>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = ishodi.content;                            //Rutiranja posebne tabele komponenete       
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
