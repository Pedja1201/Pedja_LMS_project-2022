import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MestaService } from 'src/app/service/mesta.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-mesta',
  templateUrl: './details-mesta.component.html',
  styleUrls: ['./details-mesta.component.css']
})
export class DetailsMestaComponent implements OnInit {
  mesto: any = {};

  constructor(private service: MestaService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let mestoId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(mestoId).subscribe((value: any) => {
      this.mesto = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["mesta"]);
    });
  }

  back() {
    this.location.back();
  }

}
