import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Adresa, AdresaPage } from 'src/app/model/adresa';
import { Fakultet } from 'src/app/model/fakultet';
import { Nastavnik, NastavnikPage } from 'src/app/model/nastavnik';
import { Student } from 'src/app/model/student';
import { Univerzitet, UniverzitetPage } from 'src/app/model/univerzitet';
import { AdreseService } from 'src/app/service/adrese.service';
import { NastavniciService } from 'src/app/service/nastavnici.service';
import { UniverzitetiService } from 'src/app/service/univerziteti.service';

@Component({
  selector: 'app-forma-fakulteta',
  templateUrl: './forma-fakulteta.component.html',
  styleUrls: ['./forma-fakulteta.component.css']
})
export class FormaFakultetaComponent implements OnInit {
  title='Forma Fakulteta'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;
  
  univerziteti: Univerzitet[] = [];
  adrese: Adresa[] = [];
  nastavnici: Nastavnik[] = [];
  
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "univerzitet": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "nastavnik": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  fakultet: Fakultet|null = null;

  constructor(private univerzitetiService : UniverzitetiService, private adreseService : AdreseService, 
    private nastavniciService : NastavniciService, private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.fakultet);
    this.forma.get("id")?.setValue(this.fakultet?.id);
    this.forma.get("naziv")?.setValue(this.fakultet?.naziv);
    this.forma.get("univerzitet")?.setValue(this.fakultet?.univerzitet);
    this.forma.get("adresa")?.setValue(this.fakultet?.adresa);
    this.forma.get("nastavnik")?.setValue(this.fakultet?.nastavnik);
  }

  ngOnInit(): void {
    this.univerzitetiService.getAll().subscribe((univerziteti : UniverzitetPage<Univerzitet>)=>{
      this.univerziteti = univerziteti.content;
    });
    this.adreseService.getAll().subscribe((adrese : AdresaPage<Adresa>) =>{
      this.adrese = adrese.content;
    });
    this.nastavniciService.getAll().subscribe((nastavnici : NastavnikPage<Nastavnik>) =>{
      this.nastavnici = nastavnici.content;
    });
    this.forma.get("id")?.setValue(this.fakultet?.id);
    this.forma.get("naziv")?.setValue(this.fakultet?.id);
    this.forma.get("univerzitet")?.setValue(this.fakultet?.id);
    this.forma.get("adresa")?.setValue(this.fakultet?.id);
    this.forma.get("nastavnik")?.setValue(this.fakultet?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparatorUniverzitet(univerzitet1: any, univerzitet2:any) {
    return univerzitet1 && univerzitet2
    ? univerzitet1.id === univerzitet2.id
    : univerzitet1 === univerzitet2;
  }
  //Metoda koja proverava 
  comparatorAdresa(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  //Metoda koja proverava 
  comparatorNastavnik(nastavnik1: any, nastavnik2:any) {
    return nastavnik1 && nastavnik2
    ? nastavnik1.id === nastavnik2.id
    : nastavnik1 === nastavnik2;
  }

}
