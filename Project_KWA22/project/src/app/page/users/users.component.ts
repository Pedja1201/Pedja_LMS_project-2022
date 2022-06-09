import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/model/user';
import { UsersService } from 'src/app/service/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  title="Users";
  prikaz = false;
  
  //Users
  users : User[] = [];
  userUpdate: User | null = null;

  constructor(private service : UsersService, public snackBar:MatSnackBar) {
    service.getAll().subscribe(users => {
      this.users = users;
    })
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.users = value;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.service.delete(id).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Deleted...', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  create(user: User) {
    this.service.create(user).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(user: User) {
    if(this.userUpdate && this.userUpdate.id) {
      this.service.update(this.userUpdate.id, user).subscribe((value) => {
        this.getAll();
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(user: any) {
    this.userUpdate = { ...user };
    this.prikaz = true;
  }


}
