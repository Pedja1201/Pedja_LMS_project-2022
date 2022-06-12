import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { IshodNastave } from 'src/app/model/ishod-nastave';
import { TerminNastave } from 'src/app/model/termin-nastave';
import { TipNastave } from 'src/app/model/tip-nastave';
import { IshodiNastaveService } from 'src/app/service/ishodi-nastave.service';
import { MestaService } from 'src/app/service/mesta.service';
import { TipoviNastaveService } from 'src/app/service/tipovi-nastave.service';

@Component({
  selector: 'app-form-termini-nastave',
  templateUrl: './form-termini-nastave.component.html',
  styleUrls: ['./form-termini-nastave.component.css']
})
export class FormTerminiNastaveComponent implements OnInit {
  title='Forma Termina Nsatave'

  ishodiNastave: IshodNastave[] = [];
  tipoviNastave: TipNastave[] = [];
  
  forma : FormGroup = new FormGroup({
    "vremePocetka": new FormControl(null, [Validators.required]),
    "vremeKraja": new FormControl(null, [Validators.required]),
    "ishodNastave": new FormControl(null, [Validators.required]),
    "tipNastave": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  terminNastave: TerminNastave|null = null;

  constructor(private ishodiService : IshodiNastaveService, private tipoviService : TipoviNastaveService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.terminNastave);
    this.forma.get("id")?.setValue(this.terminNastave?.id);
    this.forma.get("vremePocetka")?.setValue(this.terminNastave?.vremePocetka);
    this.forma.get("vremeKraja")?.setValue(this.terminNastave?.vremeKraja);
    this.forma.get("ishodNastave")?.setValue(this.terminNastave?.ishodNastave);
    this.forma.get("tipNastave")?.setValue(this.terminNastave?.tipNastave);

  }

  ngOnInit(): void {
    this.ishodiService.getAll().subscribe(ishodiNastave =>{
      this.ishodiNastave = ishodiNastave;
    });
    this.tipoviService.getAll().subscribe(tipoviNastave =>{
      this.tipoviNastave = tipoviNastave;
    });
    this.forma.get("id")?.setValue(this.terminNastave?.id);
    this.forma.get("vremePocetka")?.setValue(this.terminNastave?.id);
    this.forma.get("vremeKraja")?.setValue(this.terminNastave?.id);
    this.forma.get("ishodNastave")?.setValue(this.terminNastave?.id);
    this.forma.get("tipNastave")?.setValue(this.terminNastave?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(ishod1: any, ishod2:any) {
    return ishod1 && ishod2
    ? ishod1.id === ishod2.id
    : ishod1 === ishod2;
  }

  comparator2(tip1: any, tip2:any) {
    return tip1 && tip2
    ? tip1.id === tip2.id
    : tip1 === tip2;
  }

}