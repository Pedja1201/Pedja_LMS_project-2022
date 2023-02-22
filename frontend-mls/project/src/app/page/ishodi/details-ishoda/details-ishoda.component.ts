import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IshodiService } from 'src/app/service/ishodi.service';
import { Location } from '@angular/common';


@Component({
  selector: 'app-details-ishoda',
  templateUrl: './details-ishoda.component.html',
  styleUrls: ['./details-ishoda.component.css']
})
export class DetailsIshodaComponent implements OnInit {

  ishod: any = {};

  constructor(private service: IshodiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let ishodId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(ishodId).subscribe((value: any) => {
      this.ishod = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["ishodi"]);
    });
  }

  back() {
    this.location.back();
  }

}
