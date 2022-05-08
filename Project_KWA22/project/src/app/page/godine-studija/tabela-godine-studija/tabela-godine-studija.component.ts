import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { GodinaStudija } from 'src/app/model/godina-studija';
import { GodineStudijaService } from 'src/app/service/godine-studija.service';

@Component({
  selector: 'app-tabela-godine-studija',
  templateUrl: './tabela-godine-studija.component.html',
  styleUrls: ['./tabela-godine-studija.component.css']
})
export class TabelaGodineStudijaComponent implements OnInit {
  title="Tabela Godine studija";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : GodineStudijaService, private router : Router) { 
    servis.getAll().subscribe(godineStudija => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = godineStudija;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(godinaStudija: GodinaStudija) {
    this.router.navigate(["/godineStudija", godinaStudija.id]);
  }

}