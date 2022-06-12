import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { EvaluacijaZnanja } from 'src/app/model/evaluacija-znanja';
import { EvaluacijeZnanjaService } from 'src/app/service/evaluacije-znanja.service';

@Component({
  selector: 'app-table-evaluacije-znanja',
  templateUrl: './table-evaluacije-znanja.component.html',
  styleUrls: ['./table-evaluacije-znanja.component.css']
})
export class TableEvaluacijeZnanjaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'vremePocetka', 'vremeZavrsetka', 'ishod','tipEvaluacije', "akcije"];
  dataSource : EvaluacijaZnanja[]=[];
  title="Tabela Evaluacije Znanja";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private service : EvaluacijeZnanjaService, private router : Router) { 
    service.getAll().subscribe(evaluacijeZnanja => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = evaluacijeZnanja;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(evaluacijaZnanja: EvaluacijaZnanja) {
    this.router.navigate(["/evaluacijeZnanja", evaluacijaZnanja.id]);
  }

}
