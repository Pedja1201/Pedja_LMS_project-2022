import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentiNaGodiniService } from 'src/app/service/studenti-na-godini.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-studenta-na-godini',
  templateUrl: './details-studenta-na-godini.component.html',
  styleUrls: ['./details-studenta-na-godini.component.css']
})
export class DetailsStudentaNaGodiniComponent implements OnInit {
  studentNaGodini: any = {};

  constructor(private service: StudentiNaGodiniService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let studentNaGodiniId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(studentNaGodiniId).subscribe((value: any) => {
      this.studentNaGodini = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["studentiNaGodini"]);
    });
  }

  back() {
    this.location.back();
  }

}
