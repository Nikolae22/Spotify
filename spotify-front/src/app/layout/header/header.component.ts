import {Component, effect, inject, OnInit} from '@angular/core';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {AuthService} from "../../service/auth.service";
import {Location} from "@angular/common";
import {User} from "../../service/model/user.model";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [FontAwesomeModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit{

  authService=inject(AuthService)

  connectedUser : User ={email: this.authService.notConnected};

  location=inject(Location)

  ngOnInit(): void {
    this.authService.fetch();
  }


  constructor() {
    effect(()=>{
      if(this.authService.fetchUser().status == "OK"){
       this.connectedUser=this.authService.fetchUser().value!;
      }
    })
  }

  login(){
    this.authService.login();
  }

  logout(){
    this.authService.logout();
  }

  goBackward(){
    this.location.back()
  }

  goForward(){
    this.location.forward();
  }



}
