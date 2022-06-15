import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Nastavnik, NastavnikPage } from 'src/app/model/nastavnik';
import { NastavnikNaRealizaciji, NastavnikNaRealizacijiPage } from 'src/app/model/nastavnik-na-realizaciji';
import { Student } from 'src/app/model/student';
import { NastavniciNaRealizacijiService } from 'src/app/service/nastavnici-na-realizaciji.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-nastavnika-na-realizaciji',
  templateUrl: './tabela-nastavnika-na-realizaciji.component.html',
  styleUrls: ['./tabela-nastavnika-na-realizaciji.component.css']
})
export class TabelaNastavnikaNaRealizacijiComponent implements OnInit {
  displayedColumns: string[] = ['id', 'brojCasova', 'nastavnik', 'tipNastave', "akcije"];
  dataSource : NastavnikNaRealizacijiPage<NastavnikNaRealizaciji> | undefined;
  title="Tabela Nastavnika na realizaciji";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : NastavniciNaRealizacijiService, private router : Router) { 
    servis.getAll().subscribe((nastavnici : NastavnikNaRealizacijiPage<NastavnikNaRealizaciji>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = nastavnici.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(nastavnik: NastavnikNaRealizaciji) {
    this.router.navigate(["/nastavniciNaRealizaciji", nastavnik.id]);
  }

}
