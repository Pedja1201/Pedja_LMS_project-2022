import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { UniverzitetiService } from 'src/app/service/univerziteti.service';

@Component({
  selector: 'app-details-univerziteta',
  templateUrl: './details-univerziteta.component.html',
  styleUrls: ['./details-univerziteta.component.css']
})
export class DetailsUniverzitetaComponent implements OnInit {
  univerzitet: any = {};

  constructor(private service: UniverzitetiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let univerzitetId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(univerzitetId).subscribe((value: any) => {
      this.univerzitet = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["univerziteti"]);
    });
  }

  back() {
    this.location.back();
  }

}
