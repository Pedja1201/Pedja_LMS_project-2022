import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { IshodNastave } from 'src/app/model/ishod-nastave';
import { Mesto } from 'src/app/model/mesto';
import { NastavniMaterijal } from 'src/app/model/nastavni-materijal';
import { MestaService } from 'src/app/service/mesta.service';
import { NastavniMaterijaliService } from 'src/app/service/nastavni-materijali.service';

@Component({
  selector: 'app-form-ishodi-nastave',
  templateUrl: './form-ishodi-nastave.component.html',
  styleUrls: ['./form-ishodi-nastave.component.css']
})
export class FormIshodiNastaveComponent implements OnInit {
  title='Forma Ishoda Nastave'

  nastavniMaterijali: NastavniMaterijal[] = [];
  
  forma : FormGroup = new FormGroup({
    "opis": new FormControl(null, [Validators.required]),
    "nastavniMaterijal": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  ishodNastave: IshodNastave|null = null;

  constructor(private nastavniMatService : NastavniMaterijaliService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.ishodNastave);
    this.forma.get("id")?.setValue(this.ishodNastave?.id);
    this.forma.get("opis")?.setValue(this.ishodNastave?.opis);
    this.forma.get("nastavniMaterijal")?.setValue(this.ishodNastave?.nastavniMaterijal);
  }

  ngOnInit(): void {
    this.nastavniMatService.getAll().subscribe(nastavniMaterijali =>{
      this.nastavniMaterijali = nastavniMaterijali;
    });
    this.forma.get("id")?.setValue(this.ishodNastave?.id);
    this.forma.get("opis")?.setValue(this.ishodNastave?.id);
    this.forma.get("nastavniMaterijal")?.setValue(this.ishodNastave?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(mesto1: any, mesto2:any) {
    return mesto1 && mesto2
    ? mesto1.id === mesto2.id
    : mesto1 === mesto2;
  }

}
