import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { PohadjanjaPredmetaService } from 'src/app/service/pohadjanja-predmeta.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details-pohadjanja-predmeta',
  templateUrl: './details-pohadjanja-predmeta.component.html',
  styleUrls: ['./details-pohadjanja-predmeta.component.css']
})
export class DetailsPohadjanjaPredmetaComponent implements OnInit {
  pohadjanjePredmeta: any = {};

  constructor(private service: PohadjanjaPredmetaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let pohadjanjePredmetaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(pohadjanjePredmetaId).subscribe((value: any) => {
      this.pohadjanjePredmeta = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["pohadjanjaPredmeta"]);
    });
  }

  back() {
    this.location.back();
  }

}
