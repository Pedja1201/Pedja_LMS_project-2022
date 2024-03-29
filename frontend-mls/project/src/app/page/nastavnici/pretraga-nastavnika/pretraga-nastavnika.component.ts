import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Adresa, AdresaPage } from 'src/app/model/adresa';
import { Zvanje, ZvanjePage } from 'src/app/model/zvanje';
import { AdreseService } from 'src/app/service/adrese.service';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-pretraga-nastavnika',
  templateUrl: './pretraga-nastavnika.component.html',
  styleUrls: ['./pretraga-nastavnika.component.css']
})
export class PretragaNastavnikaComponent implements OnInit {

  title="Pretraga Nastavnika";

  adrese: Adresa[] = [];
  zvanja: Zvanje[] = [];


  @Output()
  pretraga: EventEmitter<any> = new EventEmitter<any>();

  parametri : FormGroup = new FormGroup({
    id: new FormControl(),
    email: new FormControl(),
    ime: new FormControl(),
    biografija: new FormControl(),
    jmbg: new FormControl(),
    adresa: new FormControl(),
    zvanje: new FormControl(),

  });

  constructor(private adreseServis : AdreseService,private zvanjaService : ZvanjaService, private router : Router) { }

  ngOnInit(): void {
    this.adreseServis.getAll().subscribe((adrese : AdresaPage<Adresa>) =>{
      this.adrese = adrese.content;
    });
    this.zvanjaService.getAll().subscribe((zvanja : ZvanjePage<Zvanje>) =>{
      this.zvanja = zvanja.content;
    });
  }


  itemTrackBy1(indeks: number, adresa: any) {
    return adresa.id;
  }
  itemTrackBy2(indeks: number, zvanje: any) {
    return zvanje.id;
  }

  pretrazi() {
    this.pretraga.emit(this.parametri.value);
  }

}
