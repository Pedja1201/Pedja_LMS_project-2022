import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NaucneOblastiService } from 'src/app/service/naucne-oblasti.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-naucne-oblasti',
  templateUrl: './details-naucne-oblasti.component.html',
  styleUrls: ['./details-naucne-oblasti.component.css']
})
export class DetailsNaucneOblastiComponent implements OnInit {
  naucnaOblast: any = {};

  constructor(private service: NaucneOblastiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let naucnaOblastId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(naucnaOblastId).subscribe((value: any) => {
      this.naucnaOblast = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["naucneOblasti"]);
    });
  }

  back() {
    this.location.back();
  }

}
