import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TerminiNastaveService } from 'src/app/service/termini-nastave.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-termini-nastave',
  templateUrl: './details-termini-nastave.component.html',
  styleUrls: ['./details-termini-nastave.component.css']
})
export class DetailsTerminiNastaveComponent implements OnInit {
  terminNastave: any = {};
  constructor(private service: TerminiNastaveService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let terminNastaveId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(terminNastaveId).subscribe((value: any) => {
      this.terminNastave = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["terminiNastave"]);
    });
  }

  back() {
    this.location.back();
  }

}
