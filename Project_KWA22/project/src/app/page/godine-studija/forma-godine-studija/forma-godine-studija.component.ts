import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { GodinaStudija } from 'src/app/model/godina-studija';
import { Predmet } from 'src/app/model/predmet';
import { Student } from 'src/app/model/student';
import { PredmetiService } from 'src/app/service/predmeti.service';

@Component({
  selector: 'app-forma-godine-studija',
  templateUrl: './forma-godine-studija.component.html',
  styleUrls: ['./forma-godine-studija.component.css']
})
export class FormaGodineStudijaComponent implements OnInit {
  title='Forma Godine studija'

  predmeti: Predmet[] = [];
  
  forma : FormGroup = new FormGroup({
    "godina": new FormControl(null, [Validators.required]),
    "predmet": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  godinaStudija: GodinaStudija|null = null;

  constructor(private predmetiService : PredmetiService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.godinaStudija);
    this.forma.get("id")?.setValue(this.godinaStudija?.id);
    this.forma.get("godina")?.setValue(this.godinaStudija?.godina);
    this.forma.get("predmet")?.setValue(this.godinaStudija?.predmet);
  }

  ngOnInit(): void {
    this.predmetiService.getAll().subscribe(predmeti =>{
      this.predmeti = predmeti;
    });
    this.forma.get("id")?.setValue(this.godinaStudija?.id);
    this.forma.get("godina")?.setValue(this.godinaStudija?.id);
    this.forma.get("predmet")?.setValue(this.godinaStudija?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(predmet1: any, predmet2:any) {
    return predmet1 && predmet2
    ? predmet1.id === predmet2.id
    : predmet1 === predmet2;
  }

}
