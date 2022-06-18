import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroupDirective, Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Adresa, AdresaPage } from 'src/app/model/adresa';
import { Nastavnik } from 'src/app/model/nastavnik';
import { Zvanje, ZvanjePage } from 'src/app/model/zvanje';
import { AdreseService } from 'src/app/service/adrese.service';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-nastavnik-register',
  templateUrl: './nastavnik-register.component.html',
  styleUrls: ['./nastavnik-register.component.css']
})
export class NastavnikRegisterComponent implements OnInit {
  title='Registrovanje Nastavnika'


  adrese: Adresa[] = [];
  zvanja: Zvanje[] = [];
  
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  trecaFormGroup = this._formBuilder.group({
    trecaCtrl: ['', Validators.required],
  });
  
  forma : FormGroup = new FormGroup({
    "korisnickoIme": new FormControl(null, [Validators.required]),
    "lozinka": new FormControl(null, [Validators.required]),
    "email": new FormControl(null, [Validators.required]),
    "ime": new FormControl(null, [Validators.required]),
    "biografija": new FormControl(null, [Validators.required]),
    "jmbg": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "zvanje": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  nastavnik: Nastavnik|null = null;

  constructor(private adreseService : AdreseService, private zvanjaService : ZvanjaService, public snackBar:MatSnackBar, private _formBuilder: FormBuilder,private router: Router) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.nastavnik);
    this.forma.get("id")?.setValue(this.nastavnik?.id);
    this.forma.get("korisnickoIme")?.setValue(this.nastavnik?.korisnickoIme);
    this.forma.get("lozinka")?.setValue(this.nastavnik?.lozinka);
    this.forma.get("email")?.setValue(this.nastavnik?.email);
    this.forma.get("ime")?.setValue(this.nastavnik?.ime);
    this.forma.get("biografija")?.setValue(this.nastavnik?.biografija);
    this.forma.get("jmbg")?.setValue(this.nastavnik?.jmbg);
    this.forma.get("adresa")?.setValue(this.nastavnik?.adresa);
    this.forma.get("zvanje")?.setValue(this.nastavnik?.zvanje)
  }

  ngOnInit(): void {
    this.adreseService.getAll().subscribe((adrese : AdresaPage<Adresa>)=>{
      this.adrese = adrese.content;
    });
    this.zvanjaService.getAll().subscribe((zvanja : ZvanjePage<Zvanje>) =>{
      this.zvanja = zvanja.content;
    });
    this.forma.get("id")?.setValue(this.nastavnik?.id);
    this.forma.get("korisnickoIme")?.setValue(this.nastavnik?.id);
    this.forma.get("lozinka")?.setValue(this.nastavnik?.id);
    this.forma.get("email")?.setValue(this.nastavnik?.id);
    this.forma.get("ime")?.setValue(this.nastavnik?.id);
    this.forma.get("biografija")?.setValue(this.nastavnik?.id);
    this.forma.get("jmbg")?.setValue(this.nastavnik?.id);
    this.forma.get("adresa")?.setValue(this.nastavnik?.id);
    this.forma.get("zvanje")?.setValue(this.nastavnik?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      let snackBarRef = this.snackBar.open('Registered as a Nastavnik', 'OK!',  {duration: 3000 });
      this.router.navigate(["login"]);
  }
}


  //Metoda koja proverava 
  comparator1(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  comparator2(zvanje1: any, zvanje2:any) {
    return zvanje1 && zvanje2
    ? zvanje1.id === zvanje2.id
    : zvanje1 === zvanje2;
  }

}
