import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentNaGodini } from 'src/app/model/student-na-godini';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-studenta-na-godini',
  templateUrl: './tabela-studenta-na-godini.component.html',
  styleUrls: ['./tabela-studenta-na-godini.component.css']
})
export class TabelaStudentaNaGodiniComponent implements OnInit {
  title="Tabela Studenata na godini";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : StudentiService, private router : Router) { 
    servis.getAll().subscribe(studentiNaGodini => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = studentiNaGodini;                            //Rutiranja posebne tabele komponenete       
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
