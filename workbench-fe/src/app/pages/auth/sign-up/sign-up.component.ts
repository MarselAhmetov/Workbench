import { ChangeDetectionStrategy, Component } from '@angular/core';
import { AuthService } from "../../../shared/services/client";
import { UserService } from "../../../shared/services/user.service";
import { flatMap, from, map, take, tap } from "rxjs";
import { Router } from "@angular/router";
import { UserLoggedInAction } from "../../../shared/store/app-actions";
import { Store } from "@ngxs/store";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  email = '';
  password = '';
  confirmPassword = '';
  subscribe = false;


  constructor(
      private readonly authService: AuthService,
      private readonly userService: UserService,
      private readonly router: Router,
      private readonly store: Store,
  ) {
  }

  signUp() {
    this.authService.signUp({
      email: this.email,
      password: this.password
    }).pipe(
        take(1),
        tap(resp => this.userService.saveToLocalStorage(resp)),
        flatMap(() => this.store.dispatch(new UserLoggedInAction())),
        map(() => this.router.navigateByUrl('/projects')),
        flatMap(p => from(p)),
    ).subscribe();

  }
}
