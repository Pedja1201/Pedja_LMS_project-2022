import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EvaluacijeZnanjaService } from 'src/app/service/evaluacije-znanja.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-evaluacije-znanja',
  templateUrl: './details-evaluacije-znanja.component.html',
  styleUrls: ['./details-evaluacije-znanja.component.css']
})
export class DetailsEvaluacijeZnanjaComponent implements OnInit {
  evaluacijaZnanja: any = {};
  constructor(private service: EvaluacijeZnanjaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let evaluacijaZnanjaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(evaluacijaZnanjaId).subscribe((value: any) => {
      this.evaluacijaZnanja = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["evaluacijeZnanja"]);
    });
  }

  back() {
    this.location.back();
  }

}
