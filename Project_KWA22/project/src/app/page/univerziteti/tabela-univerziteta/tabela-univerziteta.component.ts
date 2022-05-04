import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { Univerzitet } from 'src/app/model/univerzitet';
import { StudentiService } from 'src/app/service/studenti.service';
import { UniverzitetiService } from 'src/app/service/univerziteti.service';

@Component({
  selector: 'app-tabela-univerziteta',
  templateUrl: './tabela-univerziteta.component.html',
  styleUrls: ['./tabela-univerziteta.component.css']
})
export class TabelaUniverzitetaComponent implements OnInit {

  title="Tabela Univerziteta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : UniverzitetiService, private router : Router) { 
    servis.getAll().subscribe(univerziteti => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = univerziteti;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(univerzitet: Univerzitet) {
    this.router.navigate(["/univerziteti", univerzitet.id]);
  }

}
