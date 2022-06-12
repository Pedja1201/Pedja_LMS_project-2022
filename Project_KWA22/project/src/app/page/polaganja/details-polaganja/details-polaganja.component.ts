import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PolaganjaService } from 'src/app/service/polaganja.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-polaganja',
  templateUrl: './details-polaganja.component.html',
  styleUrls: ['./details-polaganja.component.css']
})
export class DetailsPolaganjaComponent implements OnInit {
  polaganje: any = {};
  constructor(private service: PolaganjaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let polaganjeId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(polaganjeId).subscribe((value: any) => {
      this.polaganje = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["polaganja"]);
    });
  }

  back() {
    this.location.back();
  }

}
