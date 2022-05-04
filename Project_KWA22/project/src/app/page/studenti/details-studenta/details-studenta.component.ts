import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentiService } from 'src/app/service/studenti.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-studenta',
  templateUrl: './details-studenta.component.html',
  styleUrls: ['./details-studenta.component.css']
})
export class DetailsStudentaComponent implements OnInit {
  student: any = {};

  constructor(private studentiService: StudentiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let studentId = Number(this.route.snapshot.paramMap.get("id"));
    this.studentiService.getOne(studentId).subscribe((value: any) => {
      this.student = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["studenti"]);
    });
  }

  back() {
    this.location.back();
  }

}
