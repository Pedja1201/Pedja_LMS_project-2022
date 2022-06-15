import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { EvaluacijaZnanja } from 'src/app/model/evaluacija-znanja';
import { Ishod, IshodPage } from 'src/app/model/ishod';
import { TipEvaluacije, TipEvaluacijePage } from 'src/app/model/tip-evaluacije';
import { IshodiService } from 'src/app/service/ishodi.service';
import { MestaService } from 'src/app/service/mesta.service';
import { TipoviEvaluacijeService } from 'src/app/service/tipovi-evaluacije.service';

@Component({
  selector: 'app-form-evaluacije-znanja',
  templateUrl: './form-evaluacije-znanja.component.html',
  styleUrls: ['./form-evaluacije-znanja.component.css']
})
export class FormEvaluacijeZnanjaComponent implements OnInit {
  title='Forma Evaluacije Znanja'

  ishodi: Ishod[] = [];
  tipoviEvaluacije: TipEvaluacije[] = [];
  
  forma : FormGroup = new FormGroup({
    "vremePocetka": new FormControl(null, [Validators.required]),
    "vremeZavrsetka": new FormControl(null, [Validators.required]),
    "ishod": new FormControl(null, [Validators.required]),
    "tipEvaluacije": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  evaluacijaZnanja: EvaluacijaZnanja|null = null;

  constructor(private ishodiService : IshodiService, private tipoviEvaluacijeService : TipoviEvaluacijeService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.evaluacijaZnanja);
    this.forma.get("id")?.setValue(this.evaluacijaZnanja?.id);
    this.forma.get("vremePocetka")?.setValue(this.evaluacijaZnanja?.vremePocetka);
    this.forma.get("vremeZavrsetka")?.setValue(this.evaluacijaZnanja?.vremeZavrsetka);
    this.forma.get("ishod")?.setValue(this.evaluacijaZnanja?.ishod);
    this.forma.get("tipEvaluacije")?.setValue(this.evaluacijaZnanja?.tipEvaluacije);

  }

  ngOnInit(): void {
    this.ishodiService.getAll().subscribe((ishodi : IshodPage<Ishod>)=>{
      this.ishodi = ishodi.content;
    });
    this.tipoviEvaluacijeService.getAll().subscribe((tipoviEvaluacije : TipEvaluacijePage<TipEvaluacije>)=>{
      this.tipoviEvaluacije = tipoviEvaluacije.content;
    });
    this.forma.get("id")?.setValue(this.evaluacijaZnanja?.id);
    this.forma.get("vremePocetka")?.setValue(this.evaluacijaZnanja?.id);
    this.forma.get("vremeZavrsetka")?.setValue(this.evaluacijaZnanja?.id);
    this.forma.get("ishod")?.setValue(this.evaluacijaZnanja?.id);
    this.forma.get("tipEvaluacije")?.setValue(this.evaluacijaZnanja?.id);
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
