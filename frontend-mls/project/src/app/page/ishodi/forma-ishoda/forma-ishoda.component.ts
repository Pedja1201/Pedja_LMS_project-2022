import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { Ishod } from 'src/app/model/ishod';
import { Predmet, PredmetPage } from 'src/app/model/predmet';
import { PredmetiService } from 'src/app/service/predmeti.service';

@Component({
  selector: 'app-forma-ishoda',
  templateUrl: './forma-ishoda.component.html',
  styleUrls: ['./forma-ishoda.component.css']
})
export class FormaIshodaComponent implements OnInit {
  title='Forma Ishoda'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  predmeti: Predmet[] = [];
  
  forma : FormGroup = new FormGroup({
    "opis": new FormControl(null, [Validators.required]),
    "predmet": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  ishod: Ishod|null = null;

  constructor(private predmetiService : PredmetiService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.ishod);
    this.forma.get("id")?.setValue(this.ishod?.id);
    this.forma.get("opis")?.setValue(this.ishod?.opis);
    this.forma.get("predmet")?.setValue(this.ishod?.predmet);
  }

  ngOnInit(): void {
    this.predmetiService.getAll().subscribe((predmeti : PredmetPage<Predmet>) =>{
      this.predmeti = predmeti.content;
    });
    this.forma.get("id")?.setValue(this.ishod?.id);
    this.forma.get("opis")?.setValue(this.ishod?.id);
    this.forma.get("predmet")?.setValue(this.ishod?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator(predmet1: any, predmet2:any) {
    return predmet1 && predmet2
    ? predmet1.id === predmet2.id
    : predmet1 === predmet2;
  }

}
