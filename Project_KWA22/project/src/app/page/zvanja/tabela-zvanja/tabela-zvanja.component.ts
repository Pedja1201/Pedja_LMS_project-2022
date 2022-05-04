import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Zvanje } from 'src/app/model/zvanje';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-tabela-zvanja',
  templateUrl: './tabela-zvanja.component.html',
  styleUrls: ['./tabela-zvanja.component.css']
})
export class TabelaZvanjaComponent implements OnInit {
  title="Tabela Zvanja";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : ZvanjaService, private router : Router) { 
    servis.getAll().subscribe(zvanja => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = zvanja;                            //Rutiranja posebne tabele komponenete       
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
