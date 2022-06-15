import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { Mesto, MestoPage } from 'src/app/model/mesto';
import { MestaService } from 'src/app/service/mesta.service';

@Component({
  selector: 'app-forma-adrese',
  templateUrl: './forma-adrese.component.html',
  styleUrls: ['./forma-adrese.component.css']
})
export class FormaAdreseComponent implements OnInit {
  title='Forma adrese'

  mesta: Mesto[] = [];
  
  forma : FormGroup = new FormGroup({
    "ulica": new FormControl(null, [Validators.required]),
    "broj": new FormControl(null, [Validators.required]),
    "mesto": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  adresa: Adresa|null = null;

  constructor(private mestaService : MestaService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.adresa);
    this.forma.get("id")?.setValue(this.adresa?.id);
    this.forma.get("ulica")?.setValue(this.adresa?.ulica);
    this.forma.get("broj")?.setValue(this.adresa?.broj);
    this.forma.get("mesto")?.setValue(this.adresa?.mesto);
  }

  ngOnInit(): void {
    this.mestaService.getAll().subscribe((mesta : MestoPage<Mesto>)=>{
      this.mesta = mesta.content;
    });
    this.forma.get("id")?.setValue(this.adresa?.id);
    this.forma.get("ulica")?.setValue(this.adresa?.id);
    this.forma.get("broj")?.setValue(this.adresa?.id);
    this.forma.get("mesto")?.setValue(this.adresa?.id);
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
