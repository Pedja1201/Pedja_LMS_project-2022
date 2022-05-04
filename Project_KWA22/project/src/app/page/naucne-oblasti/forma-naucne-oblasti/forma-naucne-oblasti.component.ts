import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NaucnaOblast } from 'src/app/model/naucna-oblast';
import { Zvanje } from 'src/app/model/zvanje';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-forma-naucne-oblasti',
  templateUrl: './forma-naucne-oblasti.component.html',
  styleUrls: ['./forma-naucne-oblasti.component.css']
})
export class FormaNaucneOblastiComponent implements OnInit {
  title='Forma Naucne oblasti'

  zvanja: Zvanje[] = [];
  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "zvanje": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  naucnaOblast: NaucnaOblast|null = null;

  constructor(private zvanjaService : ZvanjaService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.naucnaOblast);
    this.forma.get("id")?.setValue(this.naucnaOblast?.id);
    this.forma.get("naziv")?.setValue(this.naucnaOblast?.naziv);
    this.forma.get("zvanje")?.setValue(this.naucnaOblast?.zvanje);
  }

  ngOnInit(): void {
    this.zvanjaService.getAll().subscribe(zvanja =>{
      this.zvanja = zvanja;
    });
    this.forma.get("id")?.setValue(this.naucnaOblast?.id);
    this.forma.get("naziv")?.setValue(this.naucnaOblast?.id);
    this.forma.get("zvanje")?.setValue(this.naucnaOblast?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(zvanje1: any, zvanje2:any) {
    return zvanje1 && zvanje2
    ? zvanje1.id === zvanje2.id
    : zvanje1 === zvanje2;
  }

}
