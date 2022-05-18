import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import {AuthService} from "../../../shared/services/client";
import {UserService} from "../../../shared/services/user.service";
import {flatMap, take, tap} from "rxjs";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SignUpComponent implements OnInit {

  email = '';
  password = '';
  confirmPassword = '';
  subscribe = false;


  constructor(private readonly authService: AuthService, private readonly userService: UserService) { }

  signUp() {
    this.authService.signUp({
      email: this.email,
      password: this.password
    }).pipe(
        take(1),
        tap(resp => this.userService.saveToLocalStorage(resp))
    ).subscribe();

  }

  ngOnInit(): void {
  }

}
