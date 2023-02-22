import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { Drzava } from 'src/app/model/drzava';
import { TipEvaluacije } from 'src/app/model/tip-evaluacije';

@Component({
  selector: 'app-form-tipovi-evaluacije',
  templateUrl: './form-tipovi-evaluacije.component.html',
  styleUrls: ['./form-tipovi-evaluacije.component.css']
})
export class FormTipoviEvaluacijeComponent implements OnInit {
  title='Forma Tipa Evaluacije'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;


  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  tipEvaluacije: TipEvaluacije|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.tipEvaluacije);
    this.forma.get("id")?.setValue(this.tipEvaluacije?.id);
    this.forma.get("naziv")?.setValue(this.tipEvaluacije?.naziv)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.tipEvaluacije?.id);
    this.forma.get("naziv")?.setValue(this.tipEvaluacije?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }

}
