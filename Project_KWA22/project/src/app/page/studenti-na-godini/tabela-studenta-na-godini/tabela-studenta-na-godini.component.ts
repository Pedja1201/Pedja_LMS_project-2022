import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentNaGodini, StudentNaGodiniPage } from 'src/app/model/student-na-godini';
import { StudentiNaGodiniService } from 'src/app/service/studenti-na-godini.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-studenta-na-godini',
  templateUrl: './tabela-studenta-na-godini.component.html',
  styleUrls: ['./tabela-studenta-na-godini.component.css']
})
export class TabelaStudentaNaGodiniComponent implements OnInit {
  displayedColumns: string[] = ['id', 'datumUpisa', 'brojIndeksa', 'godinaStudija', "akcije"];
  dataSource : StudentNaGodiniPage<StudentNaGodini> | undefined;
  title="Tabela Studenata na godini";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();

                      //Izmeni servis
  constructor(private servis : StudentiNaGodiniService, private router : Router) { 
    servis.getAll().subscribe((studentiNaGodini : StudentNaGodiniPage<StudentNaGodini>) => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = studentiNaGodini.content;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(studentNaGodini: StudentNaGodini) {
    this.router.navigate(["/studentiNaGodini", studentNaGodini.id]);
  }

}
