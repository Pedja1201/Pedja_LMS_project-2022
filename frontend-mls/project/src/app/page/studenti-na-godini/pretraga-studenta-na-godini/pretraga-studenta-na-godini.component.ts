import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pretraga-studenta-na-godini',
  templateUrl: './pretraga-studenta-na-godini.component.html',
  styleUrls: ['./pretraga-studenta-na-godini.component.css']
})
export class PretragaStudentaNaGodiniComponent implements OnInit {
  title="Pretraga studenata na godini";


  @Output()
  pretraga: EventEmitter<any> = new EventEmitter<any>();

  parametri : FormGroup = new FormGroup({
    brojIndeksa: new FormControl(),


  });

  constructor( private router : Router) { }

  ngOnInit(): void {
  }


  pretrazi() {
    this.pretraga.emit(this.parametri.value);
  }

}
