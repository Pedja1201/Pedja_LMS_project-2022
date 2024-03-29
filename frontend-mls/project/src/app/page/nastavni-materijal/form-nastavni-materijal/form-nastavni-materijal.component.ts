import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Drzava } from 'src/app/model/drzava';
import { NastavniMaterijal } from 'src/app/model/nastavni-materijal';

@Component({
  selector: 'app-form-nastavni-materijal',
  templateUrl: './form-nastavni-materijal.component.html',
  styleUrls: ['./form-nastavni-materijal.component.css']
})
export class FormNastavniMaterijalComponent implements OnInit {
  title='Forma Nastavni Materijal'

  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  forma : FormGroup = new FormGroup({
    "autor": new FormControl(null, [Validators.required]),
    "godinaIzdavanja": new FormControl(null, [Validators.required]),
    "naziv": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  nastavniMaterijal: NastavniMaterijal|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.nastavniMaterijal);
    this.forma.get("id")?.setValue(this.nastavniMaterijal?.id);
    this.forma.get("autor")?.setValue(this.nastavniMaterijal?.autor)  ;
    this.forma.get("godinaIzdavanja")?.setValue(this.nastavniMaterijal?.godinaIzdavanja) ;
    this.forma.get("naziv")?.setValue(this.nastavniMaterijal?.naziv)   
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.nastavniMaterijal?.id);
    this.forma.get("autor")?.setValue(this.nastavniMaterijal?.id);
    this.forma.get("godinaIzdavanja")?.setValue(this.nastavniMaterijal?.id);
    this.forma.get("naziv")?.setValue(this.nastavniMaterijal?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }

}
