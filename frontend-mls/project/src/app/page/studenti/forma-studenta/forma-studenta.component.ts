import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Adresa, AdresaPage } from 'src/app/model/adresa';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { Student } from 'src/app/model/student';
import { StudentNaGodini, StudentNaGodiniPage } from 'src/app/model/student-na-godini';
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
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  adrese: Adresa[] = [];
  studentiNaGodini: StudentNaGodini[] = [];

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  trecaFormGroup = this._formBuilder.group({
    trecaCtrl: ['', Validators.required],
  });
  
  forma : FormGroup = new FormGroup({
    "korisnickoIme": new FormControl(null, [Validators.required]),
    "lozinka": new FormControl(null, [Validators.required]),
    "email": new FormControl(null, [Validators.required]),
    "jmbg": new FormControl(null, [Validators.required]),
    "ime": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "studentNaGodini": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  student: Student|null = null;

  constructor(private adreseService : AdreseService,private studentiNaGodiniService : StudentiNaGodiniService,  private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.student);
    this.forma.get("id")?.setValue(this.student?.id);
    this.forma.get("korisnickoIme")?.setValue(this.student?.korisnickoIme);
    this.forma.get("lozinka")?.setValue(this.student?.lozinka);
    this.forma.get("email")?.setValue(this.student?.email);
    this.forma.get("jmbg")?.setValue(this.student?.jmbg);
    this.forma.get("ime")?.setValue(this.student?.ime);
    this.forma.get("adresa")?.setValue(this.student?.adresa);
    this.forma.get("studentNaGodini")?.setValue(this.student?.studentNaGodini)
  }

  ngOnInit(): void {
    this.adreseService.getAll().subscribe((adrese : AdresaPage<Adresa>)=>{
      this.adrese = adrese.content;
    });
    this.studentiNaGodiniService.getAll().subscribe((studentiNaGodini : StudentNaGodiniPage<StudentNaGodini>) =>{
      this.studentiNaGodini = studentiNaGodini.content;
    });
    this.forma.get("id")?.setValue(this.student?.id);
    this.forma.get("korisnickoIme")?.setValue(this.student?.id);
    this.forma.get("lozinka")?.setValue(this.student?.id);
    this.forma.get("email")?.setValue(this.student?.id);
    this.forma.get("jmbg")?.setValue(this.student?.id);
    this.forma.get("ime")?.setValue(this.student?.id);
    this.forma.get("adresa")?.setValue(this.student?.id);
    this.forma.get("studentNaGodini")?.setValue(this.student?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)   
    }
  }


  //Metoda koja proverava 
  comparator1(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  comparator3(studentNaGodini1: any, studentNaGodini2:any) {
    return studentNaGodini1 && studentNaGodini2
    ? studentNaGodini1.id === studentNaGodini2.id
    : studentNaGodini1 === studentNaGodini2;
  }
}
