import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { Nastavnik, NastavnikPage } from 'src/app/model/nastavnik';
import { NastavnikNaRealizaciji } from 'src/app/model/nastavnik-na-realizaciji';
import { TipNastave, TipNastavePage } from 'src/app/model/tip-nastave';
import { NastavniciService } from 'src/app/service/nastavnici.service';
import { TipoviNastaveService } from 'src/app/service/tipovi-nastave.service';

@Component({
  selector: 'app-forma-nastavnika-na-realizaciji',
  templateUrl: './forma-nastavnika-na-realizaciji.component.html',
  styleUrls: ['./forma-nastavnika-na-realizaciji.component.css']
})
export class FormaNastavnikaNaRealizacijiComponent implements OnInit {
  title='Forma Nastavnika na realizaciji'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  nastavnici: Nastavnik[] = [];
  tipoviNastave: TipNastave[] = [];
  
  forma : FormGroup = new FormGroup({
    "brojCasova": new FormControl(null, [Validators.required]),
    "nastavnik": new FormControl(null, [Validators.required]),
    "tipNastave": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  nastavnikNaRealizaciji: NastavnikNaRealizaciji|null = null;

  constructor(private nastavniciService : NastavniciService, private tipoviNastaveService : TipoviNastaveService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.nastavnikNaRealizaciji);
    this.forma.get("id")?.setValue(this.nastavnikNaRealizaciji?.id);
    this.forma.get("brojCasova")?.setValue(this.nastavnikNaRealizaciji?.brojCasova);
    this.forma.get("nastavnik")?.setValue(this.nastavnikNaRealizaciji?.nastavnik);
    this.forma.get("tipNastave")?.setValue(this.nastavnikNaRealizaciji?.tipNastave);
  
  }

  ngOnInit(): void {
    this.nastavniciService.getAll().subscribe((nastavnici : NastavnikPage<Nastavnik>) =>{
      this.nastavnici = nastavnici.content;
    });
    this.tipoviNastaveService.getAll().subscribe((tipoviNastave : TipNastavePage<TipNastave>) =>{
      this.tipoviNastave = tipoviNastave.content;
    });
    this.forma.get("id")?.setValue(this.nastavnikNaRealizaciji?.id);
    this.forma.get("brojCasova")?.setValue(this.nastavnikNaRealizaciji?.id);
    this.forma.get("nastavnik")?.setValue(this.nastavnikNaRealizaciji?.id);
    this.forma.get("tipNastave")?.setValue(this.nastavnikNaRealizaciji?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator1(nastavnik1: any, nastavnik2:any) {
    return nastavnik1 && nastavnik2
    ? nastavnik1.id === nastavnik2.id
    : nastavnik1 === nastavnik2;
  }
  comparator2(tipNastave1: any, tipNastave2:any) {
    return tipNastave1 && tipNastave2
    ? tipNastave1.id === tipNastave2.id
    : tipNastave1 === tipNastave2;
  }

}
