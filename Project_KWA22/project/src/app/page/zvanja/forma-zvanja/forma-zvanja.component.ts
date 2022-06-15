import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NaucnaOblast, NaucnaOblastPage } from 'src/app/model/naucna-oblast';
import { Student } from 'src/app/model/student';
import { TipZvanja, TipZvanjaPage } from 'src/app/model/tip-zvanja';
import { Zvanje } from 'src/app/model/zvanje';
import { NaucneOblastiService } from 'src/app/service/naucne-oblasti.service';
import { TipoviZvanjaService } from 'src/app/service/tipovi-zvanja.service';

@Component({
  selector: 'app-forma-zvanja',
  templateUrl: './forma-zvanja.component.html',
  styleUrls: ['./forma-zvanja.component.css']
})
export class FormaZvanjaComponent implements OnInit {
  title='Forma Zvanja'

  naucneOblasti: NaucnaOblast[] = [];
  tipoviZvanja: TipZvanja[] = [];

  forma : FormGroup = new FormGroup({
    "datumIzbora": new FormControl(null, [Validators.required]),
    "datumPrestanka": new FormControl(null, [Validators.required]),
    "naucnaOblast": new FormControl(null, [Validators.required]),
    "tipZvanja": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  zvanje: Zvanje|null = null;

  constructor(private naucneOblastiService : NaucneOblastiService, private tipoviZvanjaService : TipoviZvanjaService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.zvanje);
    this.forma.get("id")?.setValue(this.zvanje?.id);
    this.forma.get("datumIzbora")?.setValue(this.zvanje?.datumIzbora);
    this.forma.get("datumPrestanka")?.setValue(this.zvanje?.datumPrestanka);
    this.forma.get("naucnaOblast")?.setValue(this.zvanje?.naucnaOblast);
    this.forma.get("tipZvanja")?.setValue(this.zvanje?.tipZvanja);

  }

  ngOnInit(): void {
    this.naucneOblastiService.getAll().subscribe((naucneOblasti : NaucnaOblastPage<NaucnaOblast>) =>{
      this.naucneOblasti = naucneOblasti.content;
    });
    this.tipoviZvanjaService.getAll().subscribe((tipoviZvanja : TipZvanjaPage<TipZvanja>) =>{
      this.tipoviZvanja = tipoviZvanja.content;
    });
    this.forma.get("id")?.setValue(this.zvanje?.id);
    this.forma.get("datumIzbora")?.setValue(this.zvanje?.id);
    this.forma.get("datumPrestanka")?.setValue(this.zvanje?.id);
    this.forma.get("naucnaOblast")?.setValue(this.zvanje?.id);
    this.forma.get("tipZvanja")?.setValue(this.zvanje?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator1(naucnaOblast1: any, naucnaOblast2:any) {
    return naucnaOblast1 && naucnaOblast2
    ? naucnaOblast1.id === naucnaOblast2.id
    : naucnaOblast1 === naucnaOblast2;
  }
  comparator2(tipZvanja1: any, tipZvanja2:any) {
    return tipZvanja1 && tipZvanja2
    ? tipZvanja1.id === tipZvanja2.id
    : tipZvanja1 === tipZvanja2;
  }

}
