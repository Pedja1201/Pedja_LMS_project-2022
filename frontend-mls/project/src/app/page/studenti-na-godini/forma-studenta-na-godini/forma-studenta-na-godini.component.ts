import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { GodinaStudija, GodinaStudijaPage } from 'src/app/model/godina-studija';
import { Student } from 'src/app/model/student';
import { StudentNaGodini } from 'src/app/model/student-na-godini';
import { GodineStudijaService } from 'src/app/service/godine-studija.service';

@Component({
  selector: 'app-forma-studenta-na-godini',
  templateUrl: './forma-studenta-na-godini.component.html',
  styleUrls: ['./forma-studenta-na-godini.component.css']
})
export class FormaStudentaNaGodiniComponent implements OnInit {
  title='Forma Studenta na godini'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  godineStudija: GodinaStudija[] = [];
  
  forma : FormGroup = new FormGroup({
    "datumUpisa": new FormControl(null, [Validators.required]),
    "brojIndeksa": new FormControl(null, [Validators.required]),
    "godinaStudija": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  studentNaGodini: StudentNaGodini|null = null;

  constructor(private godineStudijaService : GodineStudijaService, private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.studentNaGodini);
    this.forma.get("id")?.setValue(this.studentNaGodini?.id);
    this.forma.get("datumUpisa")?.setValue(this.studentNaGodini?.datumUpisa);
    this.forma.get("brojIndeksa")?.setValue(this.studentNaGodini?.brojIndeksa);
    this.forma.get("godinaStudija")?.setValue(this.studentNaGodini?.godinaStudija);

  }

  ngOnInit(): void {
    this.godineStudijaService.getAll().subscribe((godineStudija : GodinaStudijaPage<GodinaStudija>) =>{
      this.godineStudija = godineStudija.content;
    });
    this.forma.get("id")?.setValue(this.studentNaGodini?.id);
    this.forma.get("datumUpisa")?.setValue(this.studentNaGodini?.id);
    this.forma.get("brojIndeksa")?.setValue(this.studentNaGodini?.id);
    this.forma.get("godinaStudija")?.setValue(this.studentNaGodini?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator(godinaStudija1: any, godinaStudija2:any) {
    return godinaStudija1 && godinaStudija2
    ? godinaStudija1.id === godinaStudija2.id
    : godinaStudija1 === godinaStudija2;
  }

}
