import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdreseService } from 'src/app/service/adrese.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-adrese',
  templateUrl: './details-adrese.component.html',
  styleUrls: ['./details-adrese.component.css']
})
export class DetailsAdreseComponent implements OnInit {
  adresa: any = {};
  constructor(private service: AdreseService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let adresaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(adresaId).subscribe((value: any) => {
      this.adresa = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["smerovi"]);
    });
  }

  back() {
    this.location.back();
  }
}
