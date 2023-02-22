import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { TipoviEvaluacijeService } from 'src/app/service/tipovi-evaluacije.service';

@Component({
  selector: 'app-details-tipovi-evaluacije',
  templateUrl: './details-tipovi-evaluacije.component.html',
  styleUrls: ['./details-tipovi-evaluacije.component.css']
})
export class DetailsTipoviEvaluacijeComponent implements OnInit {

  tipEvaluacije: any = {};
  constructor(private service: TipoviEvaluacijeService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let tipEvaluacijeId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(tipEvaluacijeId).subscribe((value: any) => {
      this.tipEvaluacije = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["tipoviEvaluacije"]);
    });
  }

  back() {
    this.location.back();
  }

}
