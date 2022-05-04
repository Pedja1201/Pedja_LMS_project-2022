import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DrzaveService } from 'src/app/service/drzave.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-drzava',
  templateUrl: './details-drzava.component.html',
  styleUrls: ['./details-drzava.component.css']
})
export class DetailsDrzavaComponent implements OnInit {
  drzava: any = {};
  constructor(private service: DrzaveService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let drzavaId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(drzavaId).subscribe((value: any) => {
      this.drzava = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["drzave"]);
    });
  }

  back() {
    this.location.back();
  }

}
