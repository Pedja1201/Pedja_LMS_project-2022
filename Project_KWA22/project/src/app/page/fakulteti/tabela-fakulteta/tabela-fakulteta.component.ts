import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Fakultet, FakultetPage } from 'src/app/model/fakultet';
import { Student } from 'src/app/model/student';
import { FakultetiService } from 'src/app/service/fakulteti.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-fakulteta',
  templateUrl: './tabela-fakulteta.component.html',
  styleUrls: ['./tabela-fakulteta.component.css']
})
export class TabelaFakultetaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', 'univerzitet', 'adresa', 'nastavnik', "akcije"];
  dataSource : FakultetPage<Fakultet> | undefined;
  title="Tabela Fakulteta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : FakultetiService, private router : Router) { 
    servis.getAll().subscribe((fakulteti : FakultetPage<Fakultet>)=> { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = fakulteti.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(fakultet: Fakultet) {
    this.router.navigate(["/fakulteti", fakultet.id]);
  }

}
