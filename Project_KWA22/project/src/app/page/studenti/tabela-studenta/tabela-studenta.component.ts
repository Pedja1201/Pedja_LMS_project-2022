import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-tabela-studenta',
  templateUrl: './tabela-studenta.component.html',
  styleUrls: ['./tabela-studenta.component.css']
})
export class TabelaStudentaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'jmbg', 'ime', 'adresa', 'studentNaGodini', "akcije"];
  dataSource : Student[]=[];
  title="Tabela Studenata";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private studentiServis : StudentiService, private router : Router) { 
    studentiServis.getAll().subscribe(studenti => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = studenti;                            //Rutiranja posebne tabele komponenete       
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

  prikaziDetalje(student: Student) {
    this.router.navigate(["/studenti", student.id]);
  }

}
