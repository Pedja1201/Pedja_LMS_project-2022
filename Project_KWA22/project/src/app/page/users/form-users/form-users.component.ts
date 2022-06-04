import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-form-users',
  templateUrl: './form-users.component.html',
  styleUrls: ['./form-users.component.css']
})
export class FormUsersComponent implements OnInit {
  title='Form Users'

  forma : FormGroup = new FormGroup({
    "username": new FormControl(null, [Validators.required]),
    "password": new FormControl(null, [Validators.required]),
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
    this.forma.get("username")?.setValue(this.user?.username);
    this.forma.get("password")?.setValue(this.user?.password)  
  }

  ngOnInit(): void {
    this.forma.get("id")?.setValue(this.user?.id);
    this.forma.get("username")?.setValue(this.user?.id);
    this.forma.get("password")?.setValue(this.user?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }

}
