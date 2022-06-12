import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NastavniMaterijaliService } from 'src/app/service/nastavni-materijali.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-nastavni-materijal',
  templateUrl: './details-nastavni-materijal.component.html',
  styleUrls: ['./details-nastavni-materijal.component.css']
})
export class DetailsNastavniMaterijalComponent implements OnInit {

  nastavniMaterijal: any = {};
  constructor(private service: NastavniMaterijaliService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let nastavniMaterijalId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(nastavniMaterijalId).subscribe((value: any) => {
      this.nastavniMaterijal = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["nastavniMaterijali"]);
    });
  }

  back() {
    this.location.back();
  }

}
