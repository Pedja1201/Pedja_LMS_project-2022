import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { Student } from 'src/app/model/student';
import { PohadjanjaPredmetaService } from 'src/app/service/pohadjanja-predmeta.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-pohadjanja-predmeta',
  templateUrl: './tabela-pohadjanja-predmeta.component.html',
  styleUrls: ['./tabela-pohadjanja-predmeta.component.css']
})
export class TabelaPohadjanjaPredmetaComponent implements OnInit {
  title="Tabela Pohadjanja predmeta";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : PohadjanjaPredmetaService, private router : Router) { 
    servis.getAll().subscribe(pohadjanjaPredmeta => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = pohadjanjaPredmeta;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(pohadjanjePredmeta: PohadjanjePredmeta) {
    this.router.navigate(["/pohadjanjaPredmeta", pohadjanjePredmeta.id]);
  }

}