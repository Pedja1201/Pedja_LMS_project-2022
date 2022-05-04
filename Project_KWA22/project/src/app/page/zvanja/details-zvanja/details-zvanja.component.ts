import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ZvanjaService } from 'src/app/service/zvanja.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details-zvanja',
  templateUrl: './details-zvanja.component.html',
  styleUrls: ['./details-zvanja.component.css']
})
export class DetailsZvanjaComponent implements OnInit {
  zvanje: any = {};

  constructor(private service: ZvanjaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let zvanjeId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(zvanjeId).subscribe((value: any) => {
      this.zvanje = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["zvanja"]);
    });
  }

  back() {
    this.location.back();
  }

}
