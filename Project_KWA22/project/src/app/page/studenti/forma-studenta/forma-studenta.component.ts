import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { Student } from 'src/app/model/student';
import { StudentNaGodini } from 'src/app/model/student-na-godini';
import { AdreseService } from 'src/app/service/adrese.service';
import { PohadjanjaPredmetaService } from 'src/app/service/pohadjanja-predmeta.service';
import { StudentiNaGodiniService } from 'src/app/service/studenti-na-godini.service';

@Component({
  selector: 'app-forma-studenta',
  templateUrl: './forma-studenta.component.html',
  styleUrls: ['./forma-studenta.component.css']
})
export class FormaStudentaComponent implements OnInit {
  title='Forma Studenta'

  adrese: Adresa[] = [];
  pohadjanjaPredmeta: PohadjanjePredmeta[] = [];
  studentiNaGodini: StudentNaGodini[] = [];
  
  forma : FormGroup = new FormGroup({
    "jmbg": new FormControl(null, [Validators.required]),
    "ime": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "pohadjanjePredmeta": new FormControl(null, [Validators.required]),
    "studentNaGodini": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  student: Student|null = null;

  constructor(private adreseService : AdreseService,
     private pohadjanjaPredmetaService : PohadjanjaPredmetaService,private studentiNaGodiniService : StudentiNaGodiniService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.student);
    this.forma.get("id")?.setValue(this.student?.id);
    this.forma.get("jmbg")?.setValue(this.student?.jmbg);
    this.forma.get("ime")?.setValue(this.student?.ime);
    this.forma.get("adresa")?.setValue(this.student?.adresa);
    this.forma.get("pohadjanjePredmeta")?.setValue(this.student?.pohadjanjePredmeta);
    this.forma.get("studentNaGodini")?.setValue(this.student?.studentNaGodini)
  }

  ngOnInit(): void {
    this.adreseService.getAll().subscribe(adrese =>{
      this.adrese = adrese;
    });
    this.pohadjanjaPredmetaService.getAll().subscribe(pohadjanjaPredmeta =>{
      this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    });
    this.studentiNaGodiniService.getAll().subscribe(studentiNaGodini =>{
      this.studentiNaGodini = studentiNaGodini;
    });
    this.forma.get("id")?.setValue(this.student?.id);
    this.forma.get("jmbg")?.setValue(this.student?.id);
    this.forma.get("ime")?.setValue(this.student?.id);
    this.forma.get("adresa")?.setValue(this.student?.id);
    this.forma.get("pohadjanjePredmeta")?.setValue(this.student?.id);
    this.forma.get("studentNaGodini")?.setValue(this.student?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator1(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  comparator2(pohadjanjePredmeta1: any, pohadjanjePredmeta2:any) {
    return pohadjanjePredmeta1 && pohadjanjePredmeta2
    ? pohadjanjePredmeta1.id === pohadjanjePredmeta2.id
    : pohadjanjePredmeta1 === pohadjanjePredmeta2;
  }
  comparator3(studentNaGodini1: any, studentNaGodini2:any) {
    return studentNaGodini1 && studentNaGodini2
    ? studentNaGodini1.id === studentNaGodini2.id
    : studentNaGodini1 === studentNaGodini2;
  }
}
