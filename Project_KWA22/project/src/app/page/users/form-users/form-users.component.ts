import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective } from '@angular/forms';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-form-users',
  templateUrl: './form-users.component.html',
  styleUrls: ['./form-users.component.css']
})
export class FormUsersComponent implements OnInit {
  title='Form Users'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  forma : FormGroup = new FormGroup({
    "korisnickoIme": new FormControl(null, [Validators.required]),
    "lozinka": new FormControl(null, [Validators.required]),
  })
  
  @Input()
  user: User|null = null;

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.user);
    this.forma.get("id")?.setValue(this.user?.id);
    this.forma.get("korisnickoIme")?.setValue(this.user?.korisnickoIme);
    this.forma.get("lozinka")?.setValue(this.user?.lozinka)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.user?.id);
    this.forma.get("korisnickoIme")?.setValue(this.user?.id);
    this.forma.get("lozinka")?.setValue(this.user?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }

}
