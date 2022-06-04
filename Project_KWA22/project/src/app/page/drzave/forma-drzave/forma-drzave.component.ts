import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Drzava } from 'src/app/model/drzava';
import { DrzaveService } from 'src/app/service/drzave.service';

@Component({
  selector: 'app-forma-drzave',
  templateUrl: './forma-drzave.component.html',
  styleUrls: ['./forma-drzave.component.css']
})
export class FormaDrzaveComponent implements OnInit {
  title='Forma Drzave'



  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  drzava: Drzava|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.drzava);
    this.forma.get("id")?.setValue(this.drzava?.id);
    this.forma.get("naziv")?.setValue(this.drzava?.naziv)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.drzava?.id);
    this.forma.get("naziv")?.setValue(this.drzava?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }

}
