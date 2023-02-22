import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TerminNastave, TerminNastavePage } from 'src/app/model/termin-nastave';
import { TerminiNastaveService } from 'src/app/service/termini-nastave.service';

@Component({
  selector: 'app-table-termini-nastave',
  templateUrl: './table-termini-nastave.component.html',
  styleUrls: ['./table-termini-nastave.component.css']
})
export class TableTerminiNastaveComponent implements OnInit {
  displayedColumns: string[] = ['id', 'vremePocetka', 'vremeKraja', 'ishodNastave', 'tipNastave', "akcije"];
  dataSource : TerminNastavePage<TerminNastave> | undefined;
  title="Tabela Termina Nastave";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private service : TerminiNastaveService, private router : Router) { 
    service.getAll().subscribe((termini : TerminNastavePage<TerminNastave>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = termini.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(termin: TerminNastave) {
    this.router.navigate(["/terminiNastave", termin.id]);
  }

}
