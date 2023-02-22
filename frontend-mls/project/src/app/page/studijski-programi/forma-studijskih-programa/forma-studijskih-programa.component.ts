import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Fakultet, FakultetPage } from 'src/app/model/fakultet';
import { GodinaStudija, GodinaStudijaPage } from 'src/app/model/godina-studija';
import { Nastavnik, NastavnikPage } from 'src/app/model/nastavnik';
import { Student } from 'src/app/model/student';
import { StudijskiProgram } from 'src/app/model/studijski-program';
import { FakultetiService } from 'src/app/service/fakulteti.service';
import { GodineStudijaService } from 'src/app/service/godine-studija.service';
import { NastavniciService } from 'src/app/service/nastavnici.service';

@Component({
  selector: 'app-forma-studijskih-programa',
  templateUrl: './forma-studijskih-programa.component.html',
  styleUrls: ['./forma-studijskih-programa.component.css']
})
export class FormaStudijskihProgramaComponent implements OnInit {
  title='Forma Studijskih programa'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  fakulteti: Fakultet[] = [];
  nastavnici: Nastavnik[] = [];
  godineStudija: GodinaStudija[] = [];
  
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "fakultet": new FormControl(null, [Validators.required]),
    "nastavnik": new FormControl(null, [Validators.required]),
    "godinaStudija": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  studijskiProgram: StudijskiProgram|null = null;

  constructor(private fakultetiService : FakultetiService,private nastavniciService : NastavniciService,
    private godineStudijaService : GodineStudijaService, private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.studijskiProgram);
    this.forma.get("id")?.setValue(this.studijskiProgram?.id);
    this.forma.get("naziv")?.setValue(this.studijskiProgram?.naziv);
    this.forma.get("fakultet")?.setValue(this.studijskiProgram?.fakultet);
    this.forma.get("nastavnik")?.setValue(this.studijskiProgram?.nastavnik);
    this.forma.get("godinaStudija")?.setValue(this.studijskiProgram?.godinaStudija);
  }

  ngOnInit(): void {
    this.fakultetiService.getAll().subscribe((fakulteti : FakultetPage<Fakultet>) =>{
      this.fakulteti = fakulteti.content;
    });
    this.nastavniciService.getAll().subscribe((nastavnici : NastavnikPage<Nastavnik>) =>{
      this.nastavnici = nastavnici.content;
    });
    this.godineStudijaService.getAll().subscribe((godineStudija : GodinaStudijaPage<GodinaStudija>) =>{
      this.godineStudija = godineStudija.content;
    });
    this.forma.get("id")?.setValue(this.studijskiProgram?.id);
    this.forma.get("naziv")?.setValue(this.studijskiProgram?.id);
    this.forma.get("fakultet")?.setValue(this.studijskiProgram?.id);
    this.forma.get("nastavnik")?.setValue(this.studijskiProgram?.id);
    this.forma.get("godinaStudija")?.setValue(this.studijskiProgram?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator1(fakultet1: any, fakultet2:any) {
    return fakultet1 && fakultet2
    ? fakultet1.id === fakultet2.id
    : fakultet1 === fakultet2;
  }
  comparator2(nastavnik1: any, nastavnik2:any) {
    return nastavnik1 && nastavnik2
    ? nastavnik1.id === nastavnik2.id
    : nastavnik1 === nastavnik2;
  }
  comparator3(godinaStudija1: any, godinaStudija2:any) {
    return godinaStudija1 && godinaStudija2
    ? godinaStudija1.id === godinaStudija2.id
    : godinaStudija1 === godinaStudija2;
  }

}
