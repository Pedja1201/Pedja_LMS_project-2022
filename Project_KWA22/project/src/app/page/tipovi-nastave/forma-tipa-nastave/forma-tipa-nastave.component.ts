import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { TipNastave } from 'src/app/model/tip-nastave';

@Component({
  selector: 'app-forma-tipa-nastave',
  templateUrl: './forma-tipa-nastave.component.html',
  styleUrls: ['./forma-tipa-nastave.component.css']
})
export class FormaTipaNastaveComponent implements OnInit {
  title='Forma Tipa nastave'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  tipNastave: TipNastave|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.tipNastave);
    this.forma.get("id")?.setValue(this.tipNastave?.id);
    this.forma.get("naziv")?.setValue(this.tipNastave?.naziv)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.tipNastave?.id);
    this.forma.get("naziv")?.setValue(this.tipNastave?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }

}
