import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { EvaluacijaZnanja, EvaluacijaZnanjaPage } from 'src/app/model/evaluacija-znanja';
import { Polaganje } from 'src/app/model/polaganje';
import { StudentNaGodini, StudentNaGodiniPage } from 'src/app/model/student-na-godini';
import { EvaluacijeZnanjaService } from 'src/app/service/evaluacije-znanja.service';
import { MestaService } from 'src/app/service/mesta.service';
import { StudentiNaGodiniService } from 'src/app/service/studenti-na-godini.service';

@Component({
  selector: 'app-form-polaganja',
  templateUrl: './form-polaganja.component.html',
  styleUrls: ['./form-polaganja.component.css']
})
export class FormPolaganjaComponent implements OnInit {
  title='Forma Polaganja'
  
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  evaluacijeZnanja: EvaluacijaZnanja[] = [];
  studentiNaGodini: StudentNaGodini[] = [];
  
  forma : FormGroup = new FormGroup({
    "bodovi": new FormControl(null, [Validators.required]),
    "napomena": new FormControl(null, [Validators.required]),
    "evaluacijaZnanja": new FormControl(null, [Validators.required]),
    "studentNaGodini": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  polaganje: Polaganje|null = null;

  constructor(private evZnanjaervice : EvaluacijeZnanjaService, private studentiNaGod : StudentiNaGodiniService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.polaganje);
    this.forma.get("id")?.setValue(this.polaganje?.id);
    this.forma.get("bodovi")?.setValue(this.polaganje?.bodovi);
    this.forma.get("napomena")?.setValue(this.polaganje?.napomena);
    this.forma.get("evaluacijaZnanja")?.setValue(this.polaganje?.evaluacijaZnanja);
    this.forma.get("studentNaGodini")?.setValue(this.polaganje?.studentNaGodini);

  }

  ngOnInit(): void {
    this.evZnanjaervice.getAll().subscribe((evaluacijeZnanja : EvaluacijaZnanjaPage<EvaluacijaZnanja>) =>{
      this.evaluacijeZnanja = evaluacijeZnanja.content;
    });
    this.studentiNaGod.getAll().subscribe((studentiNaGodini : StudentNaGodiniPage<StudentNaGodini>) =>{
      this.studentiNaGodini = studentiNaGodini.content;
    });
    this.forma.get("id")?.setValue(this.polaganje?.id);
    this.forma.get("bodovi")?.setValue(this.polaganje?.id);
    this.forma.get("napomena")?.setValue(this.polaganje?.id);
    this.forma.get("evaluacijaZnanja")?.setValue(this.polaganje?.id);
    this.forma.get("studentNaGodini")?.setValue(this.polaganje?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator(evZnanja1: any, evZnanja2:any) {
    return evZnanja1 && evZnanja2
    ? evZnanja1.id === evZnanja2.id
    : evZnanja1 === evZnanja2;
  }
  //Metoda koja proverava 
  comparator2(studenti1: any, studenti2:any) {
    return studenti1 && studenti2
    ? studenti1.id === studenti2.id
    : studenti1 === studenti2;
  }

}
