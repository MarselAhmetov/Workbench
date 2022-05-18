import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';
import {AuthService} from "../../../shared/services/client";
import {UserService} from "../../../shared/services/user.service";
import {take, tap} from "rxjs";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SignInComponent implements OnInit {

  email = '';
  password = '';
  rememberMe = false;

  constructor(private readonly authService: AuthService, private readonly userService: UserService) {
  }

  signIn() {
    this.authService.signIn({
      email: this.email,
      password: this.password
    }).pipe(
        take(1),
        tap(resp => this.userService.saveToLocalStorage(resp))
    ).subscribe()
  }

  ngOnInit(): void {
  }

}
