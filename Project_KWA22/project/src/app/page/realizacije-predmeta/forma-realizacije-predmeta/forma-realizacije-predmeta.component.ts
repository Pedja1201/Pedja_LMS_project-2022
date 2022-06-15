import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { EvaluacijaZnanja, EvaluacijaZnanjaPage } from 'src/app/model/evaluacija-znanja';
import { NastavnikNaRealizaciji, NastavnikNaRealizacijiPage } from 'src/app/model/nastavnik-na-realizaciji';
import { Predmet, PredmetPage } from 'src/app/model/predmet';
import { RealizacijaPredmeta } from 'src/app/model/realizacija-predmeta';
import { TerminNastave, TerminNastavePage } from 'src/app/model/termin-nastave';
import { EvaluacijeZnanjaService } from 'src/app/service/evaluacije-znanja.service';
import { NastavniciNaRealizacijiService } from 'src/app/service/nastavnici-na-realizaciji.service';
import { PredmetiService } from 'src/app/service/predmeti.service';
import { TerminiNastaveService } from 'src/app/service/termini-nastave.service';

@Component({
  selector: 'app-forma-realizacije-predmeta',
  templateUrl: './forma-realizacije-predmeta.component.html',
  styleUrls: ['./forma-realizacije-predmeta.component.css']
})
export class FormaRealizacijePredmetaComponent implements OnInit {
  title='Forma Realizacije predmeta'

  nastavniciNaRealizaciji: NastavnikNaRealizaciji[] = [];
  predmeti: Predmet[] = [];
  evaluacijeZnanja : EvaluacijaZnanja[] = [];
  terminiNastave : TerminNastave[] = [];
  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "nastavnikNaRealizaciji": new FormControl(null, [Validators.required]),
    "predmet": new FormControl(null, [Validators.required]),
    "evaluacijaZnanja": new FormControl(null, [Validators.required]),
    "terminNastave": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  realizacijaPredmeta: RealizacijaPredmeta|null = null;

  constructor(private nastavniciNaRealizacijiService : NastavniciNaRealizacijiService, 
    private predmetiService : PredmetiService,  private evZnanjaService : EvaluacijeZnanjaService, private terminService : TerminiNastaveService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.realizacijaPredmeta);
    this.forma.get("id")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("naziv")?.setValue(this.realizacijaPredmeta?.naziv);
    this.forma.get("nastavnikNaRealizaciji")?.setValue(this.realizacijaPredmeta?.nastavnikNaRealizaciji);
    this.forma.get("predmet")?.setValue(this.realizacijaPredmeta?.predmet);
    this.forma.get("evaluacijaZnanja")?.setValue(this.realizacijaPredmeta?.evaluacijaZnanja);
    this.forma.get("terminNastave")?.setValue(this.realizacijaPredmeta?.terminNastave);

  }

  ngOnInit(): void {
    this.nastavniciNaRealizacijiService.getAll().subscribe((nastavniciNaRealizaciji : NastavnikNaRealizacijiPage<NastavnikNaRealizaciji>) =>{
      this.nastavniciNaRealizaciji = nastavniciNaRealizaciji.content;
    });
    this.predmetiService.getAll().subscribe((predmeti : PredmetPage<Predmet>) =>{
      this.predmeti = predmeti.content;
    });
    this.evZnanjaService.getAll().subscribe((evaluacijeZnanja : EvaluacijaZnanjaPage<EvaluacijaZnanja>) =>{
      this.evaluacijeZnanja = evaluacijeZnanja.content;
    });
    this.terminService.getAll().subscribe((terminiNastave : TerminNastavePage<TerminNastave>) =>{
      this.terminiNastave = terminiNastave.content;
    });
    this.forma.get("id")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("naziv")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("nastavnikNaRealizaciji")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("predmet")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("evaluacijaZnanja")?.setValue(this.realizacijaPredmeta?.id);
    this.forma.get("terminNastave")?.setValue(this.realizacijaPredmeta?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator1(nastavnikNaRealizaciji1: any, nastavnikNaRealizaciji2:any) {
    return nastavnikNaRealizaciji1 && nastavnikNaRealizaciji2
    ? nastavnikNaRealizaciji1.id === nastavnikNaRealizaciji2.id
    : nastavnikNaRealizaciji1 === nastavnikNaRealizaciji2;
  }
  comparator2(predmet1: any, predmet2:any) {
    return predmet1 && predmet2
    ? predmet1.id === predmet2.id
    : predmet1 === predmet2;
  }

  comparator3(evZnanja1: any, evZnanja2:any) {
    return evZnanja1 && evZnanja2
    ? evZnanja1.id === evZnanja2.id
    : evZnanja1 === evZnanja2;
  }

  comparator4(termin1: any, termin2:any) {
    return termin1 && termin2
    ? termin1.id === termin2.id
    : termin1 === termin2;
  }
}
