import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TipNastave } from 'src/app/model/tip-nastave';
import { TipZvanja } from 'src/app/model/tip-zvanja';

@Component({
  selector: 'app-forma-tipa-zvanja',
  templateUrl: './forma-tipa-zvanja.component.html',
  styleUrls: ['./forma-tipa-zvanja.component.css']
})
export class FormaTipaZvanjaComponent implements OnInit {

  title='Forma Tipa zvanja'

  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  tipZvanja: TipZvanja|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.tipZvanja);
    this.forma.get("id")?.setValue(this.tipZvanja?.id);
    this.forma.get("naziv")?.setValue(this.tipZvanja?.naziv)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.tipZvanja?.id);
    this.forma.get("naziv")?.setValue(this.tipZvanja?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }

}
