import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NastavniciNaRealizacijiService } from 'src/app/service/nastavnici-na-realizaciji.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-nastavnika-na-realizaciji',
  templateUrl: './details-nastavnika-na-realizaciji.component.html',
  styleUrls: ['./details-nastavnika-na-realizaciji.component.css']
})
export class DetailsNastavnikaNaRealizacijiComponent implements OnInit {
  nastavnikNaRealizaciji: any = {};

  constructor(private service: NastavniciNaRealizacijiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let nastavnikNaRealizacijiId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(nastavnikNaRealizacijiId).subscribe((value: any) => {
      this.nastavnikNaRealizaciji = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["nastavniciNaRealizaciji"]);
    });
  }

  back() {
    this.location.back();
  }

}
