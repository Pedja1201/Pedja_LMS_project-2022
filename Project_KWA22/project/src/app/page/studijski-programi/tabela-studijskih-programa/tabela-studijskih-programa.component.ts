import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudijskiProgram } from 'src/app/model/studijski-program';
import { StudentiService } from 'src/app/service/studenti.service';
import { StudijskiProgramiService } from 'src/app/service/studijski-programi.service';

@Component({
  selector: 'app-tabela-studijskih-programa',
  templateUrl: './tabela-studijskih-programa.component.html',
  styleUrls: ['./tabela-studijskih-programa.component.css']
})
export class TabelaStudijskihProgramaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'naziv', 'fakultet', 'nastavnik', 'godinaStudija', "akcije"];
  dataSource : StudijskiProgram[]=[];
  title="Tabela Studijskih programa";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : StudijskiProgramiService, private router : Router) { 
    servis.getAll().subscribe(studijskiProgrami => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = studijskiProgrami;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(studijskiProgram: StudijskiProgram) {
    this.router.navigate(["/studijskiProgrami", studijskiProgram.id]);
  }

}
