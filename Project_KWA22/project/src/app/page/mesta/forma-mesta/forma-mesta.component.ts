import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Drzava } from 'src/app/model/drzava';
import { Mesto } from 'src/app/model/mesto';
import { Student } from 'src/app/model/student';
import { DrzaveService } from 'src/app/service/drzave.service';

@Component({
  selector: 'app-forma-mesta',
  templateUrl: './forma-mesta.component.html',
  styleUrls: ['./forma-mesta.component.css']
})
export class FormaMestaComponent implements OnInit {

  title='Forma mesta'

  drzave: Drzava[] = [];
  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "drzava": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  mesto: Mesto|null = null;

  constructor(private drzaveService : DrzaveService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.mesto);
    this.forma.get("id")?.setValue(this.mesto?.id);
    this.forma.get("naziv")?.setValue(this.mesto?.naziv);
    this.forma.get("drzava")?.setValue(this.mesto?.drzava);
  }

  ngOnInit(): void {
    this.drzaveService.getAll().subscribe(drzave =>{
      this.drzave = drzave;
    });
    this.forma.get("id")?.setValue(this.mesto?.id);
    this.forma.get("naziv")?.setValue(this.mesto?.id);
    this.forma.get("drzava")?.setValue(this.mesto?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(drzava1: any, drzava2:any) {
    return drzava1 && drzava2
    ? drzava1.id === drzava2.id
    : drzava1 === drzava2;
  }

}
