import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IshodiNastaveService } from 'src/app/service/ishodi-nastave.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-ishodi-nastave',
  templateUrl: './details-ishodi-nastave.component.html',
  styleUrls: ['./details-ishodi-nastave.component.css']
})
export class DetailsIshodiNastaveComponent implements OnInit {
  ishodNastave: any = {};
  constructor(private service: IshodiNastaveService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let ishodNastaveId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(ishodNastaveId).subscribe((value: any) => {
      this.ishodNastave = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["ishodiNastave"]);
    });
  }

  back() {
    this.location.back();
  }

}
