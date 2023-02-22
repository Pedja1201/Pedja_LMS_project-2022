import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NaucnaOblast, NaucnaOblastPage } from 'src/app/model/naucna-oblast';
import { LoginService } from 'src/app/service/login.service';
import { NaucneOblastiService } from 'src/app/service/naucne-oblasti.service';

@Component({
  selector: 'app-tabela-naucne-oblasti',
  templateUrl: './tabela-naucne-oblasti.component.html',
  styleUrls: ['./tabela-naucne-oblasti.component.css']
})
export class TabelaNaucneOblastiComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', "akcije"];
  dataSource : NaucnaOblastPage<NaucnaOblast> | undefined;
  title="Tabela Naucnih oblasti";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : NaucneOblastiService, private router : Router,  public loginService : LoginService) { 
    servis.getAll().subscribe((naucneOblasti : NaucnaOblastPage<NaucnaOblast>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = naucneOblasti.content;                            //Rutiranja posebne tabele komponenete       
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
