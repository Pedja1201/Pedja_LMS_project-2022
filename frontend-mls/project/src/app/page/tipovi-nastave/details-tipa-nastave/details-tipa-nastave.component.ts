import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoviNastaveService } from 'src/app/service/tipovi-nastave.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-details-tipa-nastave',
  templateUrl: './details-tipa-nastave.component.html',
  styleUrls: ['./details-tipa-nastave.component.css']
})
export class DetailsTipaNastaveComponent implements OnInit {
  tipNastave: any = {};
  constructor(private service: TipoviNastaveService, private route: ActivatedRoute, private router: Router, private location: Location) { }

  ngOnInit(): void {
    let tipNastaveId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(tipNastaveId).subscribe((value: any) => {
      this.tipNastave = value;
    }, (error) => {
      console.log(error);
      this.router.navigate(["tipoviNastave"]);
    });
  }

  back() {
    this.location.back();
  }

}
