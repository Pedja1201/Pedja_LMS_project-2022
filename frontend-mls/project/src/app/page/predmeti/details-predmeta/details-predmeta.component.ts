import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PredmetiService } from 'src/app/service/predmeti.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-predmeta',
  templateUrl: './details-predmeta.component.html',
  styleUrls: ['./details-predmeta.component.css']
})
export class DetailsPredmetaComponent implements OnInit {
  predmet: any = {};
  constructor(private service: PredmetiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let predmetId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(predmetId).subscribe((value: any) => {
      this.predmet = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["predmeti"]);
    });
  }

  back() {
    this.location.back();
  }

}
