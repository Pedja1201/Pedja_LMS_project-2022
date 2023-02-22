import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Zvanje, ZvanjePage } from 'src/app/model/zvanje';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-tabela-zvanja',
  templateUrl: './tabela-zvanja.component.html',
  styleUrls: ['./tabela-zvanja.component.css']
})
export class TabelaZvanjaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'datumIzbora', 'datumPrestanka', 'naucnaOblast', 'tipZvanja', "akcije"];
  dataSource : ZvanjePage<Zvanje> | undefined;
  title="Tabela Zvanja";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : ZvanjaService, private router : Router) { 
    servis.getAll().subscribe((zvanja : ZvanjePage<Zvanje>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = zvanja.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(zvanje: Zvanje) {
    this.router.navigate(["/zvanja", zvanje.id]);
  }

}
