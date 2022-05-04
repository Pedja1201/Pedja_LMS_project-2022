import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Adresa } from 'src/app/model/adresa';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { StudentNaGodini } from 'src/app/model/student-na-godini';
import { AdreseService } from 'src/app/service/adrese.service';
import { PohadjanjaPredmetaService } from 'src/app/service/pohadjanja-predmeta.service';
import { StudentiNaGodiniService } from 'src/app/service/studenti-na-godini.service';

@Component({
  selector: 'app-pretraga-studenta',
  templateUrl: './pretraga-studenta.component.html',
  styleUrls: ['./pretraga-studenta.component.css']
})
export class PretragaStudentaComponent implements OnInit {
  title="Pretraga studenta";

  adrese: Adresa[] = [];
  pohadjanjaPredmeta: PohadjanjePredmeta[] = [];
  studentiNaGodini: StudentNaGodini[] = [];

  @Output()
  pretraga: EventEmitter<any> = new EventEmitter<any>();

  parametri : FormGroup = new FormGroup({
    id: new FormControl(),
    jmbg: new FormControl(),
    ime: new FormControl(),
    adresa: new FormControl(),
    pohadjanjePredmeta: new FormControl(),
    studentNaGodini: new FormControl(),

  });

  constructor(private adreseServis : AdreseService,private pohadjanjaPredmetaServis : PohadjanjaPredmetaService,
          private studentiNaGodiniServis : StudentiNaGodiniService, private router : Router) { }

  ngOnInit(): void {
    this.adreseServis.getAll().subscribe(adrese =>{
      this.adrese = adrese;
    });
    this.pohadjanjaPredmetaServis.getAll().subscribe(pohadjanjaPredmeta =>{
      this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    });
    this.studentiNaGodiniServis.getAll().subscribe(studentiNaGodini =>{
      this.studentiNaGodini = studentiNaGodini;
    });
  }


  itemTrackBy1(indeks: number, adresa: any) {
    return adresa.id;
  }
  itemTrackBy2(indeks: number, pohadjanjePredmeta: any) {
    return pohadjanjePredmeta.id;
  }
  itemTrackBy3(indeks: number, studentNaGodini: any) {
    return studentNaGodini.id;
  }

  pretrazi() {
    this.pretraga.emit(this.parametri.value);
  }

}
