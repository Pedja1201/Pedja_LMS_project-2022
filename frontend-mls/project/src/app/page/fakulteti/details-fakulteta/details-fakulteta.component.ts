import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { FakultetiService } from 'src/app/service/fakulteti.service';

@Component({
  selector: 'app-details-fakulteta',
  templateUrl: './details-fakulteta.component.html',
  styleUrls: ['./details-fakulteta.component.css']
})
export class DetailsFakultetaComponent implements OnInit {
  fakultet: any = {};

  constructor(private service: FakultetiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let studentId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(studentId).subscribe((value: any) => {
      this.fakultet = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["fakulteti"]);
    });
  }

  back() {
    this.location.back();
  }

}
