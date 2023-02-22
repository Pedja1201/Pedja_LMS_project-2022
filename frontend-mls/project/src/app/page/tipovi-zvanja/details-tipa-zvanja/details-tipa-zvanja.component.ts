import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoviZvanjaService } from 'src/app/service/tipovi-zvanja.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-tipa-zvanja',
  templateUrl: './details-tipa-zvanja.component.html',
  styleUrls: ['./details-tipa-zvanja.component.css']
})
export class DetailsTipaZvanjaComponent implements OnInit {
  tipZvanja: any = {};
  constructor(private service: TipoviZvanjaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let tipZvanjaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(tipZvanjaId).subscribe((value: any) => {
      this.tipZvanja = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["tipoviZvanja"]);
    });
  }

  back() {
    this.location.back();
  }

}
