import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NaucnaOblast } from 'src/app/model/naucna-oblast';
import { NaucneOblastiService } from 'src/app/service/naucne-oblasti.service';

@Component({
  selector: 'app-tabela-naucne-oblasti',
  templateUrl: './tabela-naucne-oblasti.component.html',
  styleUrls: ['./tabela-naucne-oblasti.component.css']
})
export class TabelaNaucneOblastiComponent implements OnInit {

  title="Tabela Naucnih oblasti";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : NaucneOblastiService, private router : Router) { 
    servis.getAll().subscribe(naucneOblasti => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = naucneOblasti;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(naucnaOblast: NaucnaOblast) {
    this.router.navigate(["/naucneOblasti", naucnaOblast.id]);
  }

}
