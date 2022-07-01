import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Predmet } from 'src/app/model/predmet';

@Component({
  selector: 'app-forma-predmeta',
  templateUrl: './forma-predmeta.component.html',
  styleUrls: ['./forma-predmeta.component.css']
})
export class FormaPredmetaComponent implements OnInit {
  title='Forma Predmeta'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "espb": new FormControl(null, [Validators.required]),
    "obavezan": new FormControl(null),
    "brojPredavanja": new FormControl(null, [Validators.required]),
    "brojVezbi": new FormControl(null, [Validators.required]),
    "drugiObliciNastave": new FormControl(null, [Validators.required]),
    "istrazivackiRad": new FormControl(null, [Validators.required]),
    "ostaliCasovi": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  predmet: Predmet|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.predmet);
    this.forma.get("id")?.setValue(this.predmet?.id);
    this.forma.get("naziv")?.setValue(this.predmet?.naziv);
    this.forma.get("espb")?.setValue(this.predmet?.espb);
    this.forma.get("obavezan")?.setValue(this.predmet?.obavezan) ; 
    this.forma.get("brojPredavanja")?.setValue(this.predmet?.brojPredavanja)  ;
    this.forma.get("brojVezbi")?.setValue(this.predmet?.brojVezbi) ; 
    this.forma.get("drugiObliciNastave")?.setValue(this.predmet?.drugiObliciNastave) ; 
    this.forma.get("istrazivackiRad")?.setValue(this.predmet?.istrazivackiRad) ;
    this.forma.get("ostaliCasovi")?.setValue(this.predmet?.ostaliCasovi)       
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.predmet?.id);
    this.forma.get("naziv")?.setValue(this.predmet?.id);
    this.forma.get("espb")?.setValue(this.predmet?.id);
    this.forma.get("obavezan")?.setValue(this.predmet?.id);
    this.forma.get("brojPredavanja")?.setValue(this.predmet?.id);
    this.forma.get("brojVezbi")?.setValue(this.predmet?.id);
    this.forma.get("drugiObliciNastave")?.setValue(this.predmet?.id);
    this.forma.get("istrazivackiRad")?.setValue(this.predmet?.id);
    this.forma.get("ostaliCasovi")?.setValue(this.predmet?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }

}
