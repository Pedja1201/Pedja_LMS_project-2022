import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Mesto, MestoPage } from 'src/app/model/mesto';
import { Student } from 'src/app/model/student';
import { LoginService } from 'src/app/service/login.service';
import { MestaService } from 'src/app/service/mesta.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-mesta',
  templateUrl: './tabela-mesta.component.html',
  styleUrls: ['./tabela-mesta.component.css']
})
export class TabelaMestaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', 'drzava', "akcije"];
  dataSource : MestoPage<Mesto> | undefined;
  title="Tabela Mesta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : MestaService, private router : Router, public loginService : LoginService) { 
    servis.getAll().subscribe((mesta : MestoPage<Mesto>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = mesta.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(mesto: Student) {
    this.router.navigate(["/mesta", mesto.id]);
  }

}
