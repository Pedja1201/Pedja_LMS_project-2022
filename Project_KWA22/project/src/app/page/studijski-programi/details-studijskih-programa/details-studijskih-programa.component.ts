import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudijskiProgramiService } from 'src/app/service/studijski-programi.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-studijskih-programa',
  templateUrl: './details-studijskih-programa.component.html',
  styleUrls: ['./details-studijskih-programa.component.css']
})
export class DetailsStudijskihProgramaComponent implements OnInit {
  studijskiProgram: any = {};

  constructor(private service: StudijskiProgramiService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let studijskiProgramId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(studijskiProgramId).subscribe((value: any) => {
      this.studijskiProgram = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["studijskiProgrami"]);
    });
  }

  back() {
    this.location.back();
  }

}
