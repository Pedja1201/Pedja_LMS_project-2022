import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { GodineStudijaService } from 'src/app/service/godine-studija.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details-godine-studija',
  templateUrl: './details-godine-studija.component.html',
  styleUrls: ['./details-godine-studija.component.css']
})
export class DetailsGodineStudijaComponent implements OnInit {

  godinaStudija: any = {};

  constructor(private service: GodineStudijaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let godinaStudijaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(godinaStudijaId).subscribe((value: any) => {
      this.godinaStudija = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["godineStudija"]);
    });
  }

  back() {
    this.location.back();
  }

}
