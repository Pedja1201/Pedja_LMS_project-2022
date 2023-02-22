import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { RealizacijePredmetaService } from 'src/app/service/realizacije-predmeta.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details-realizacije-predmeta',
  templateUrl: './details-realizacije-predmeta.component.html',
  styleUrls: ['./details-realizacije-predmeta.component.css']
})
export class DetailsRealizacijePredmetaComponent implements OnInit {
  realizacijaPredmeta: any = {};

  constructor(private service: RealizacijePredmetaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let realizacijaPredmetaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(realizacijaPredmetaId).subscribe((value: any) => {
      this.realizacijaPredmeta = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["realizacijePredmeta"]);
    });
  }

  back() {
    this.location.back();
  }

}
