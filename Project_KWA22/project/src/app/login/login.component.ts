import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title = "Login page"

  loginForma : FormGroup = new FormGroup({
    "username": new FormControl(null, Validators.required),
    "password": new FormControl(null, Validators.required),
  });
  loginFailed = false;

  //Mora public da bi pokupio logout!!!
  constructor(public loginService : LoginService, private router : Router) { }

  ngOnInit(): void {
  }

   //Login
  login(){
    if(this.loginForma.valid){
      this.loginService.login(this.loginForma.value).subscribe(r => {
        console.log(r);
        this.router.navigate(["/univerziteti"]);//Prebacivanje nakon logina na page
      }, (err) => {
        this.loginFailed = true;
      });
    }
  }

}
