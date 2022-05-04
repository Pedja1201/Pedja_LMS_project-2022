import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NastavniciService } from 'src/app/service/nastavnici.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-nastavnika',
  templateUrl: './details-nastavnika.component.html',
  styleUrls: ['./details-nastavnika.component.css']
})
export class DetailsNastavnikaComponent implements OnInit {

  nastavnik: any = {};

  constructor(private service: NastavniciService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let nastavnikId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(nastavnikId).subscribe((value: any) => {
      this.nastavnik = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["nastavnici"]);
    });
  }

  back() {
    this.location.back();
  }

}
