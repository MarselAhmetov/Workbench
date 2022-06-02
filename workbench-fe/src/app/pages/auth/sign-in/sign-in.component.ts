import { ChangeDetectionStrategy, Component, OnDestroy } from '@angular/core';
import { AuthService } from "../../../shared/services/client";
import { UserService } from "../../../shared/services/user.service";
import { flatMap, from, map, Observable, Subscription, take, tap } from "rxjs";
import { Router } from "@angular/router";
import { Store } from "@ngxs/store";
import { UserLoggedInAction } from "../../../shared/store/app-actions";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SignInComponent implements OnDestroy {

  email = '';
  password = '';
  rememberMe = false;

  private auth$?: Subscription;

  constructor(
      private readonly authService: AuthService,
      private readonly userService: UserService,
      private readonly router: Router,
      private readonly store: Store,
  ) {
  }

  signIn() {
    this.auth$ = this.authService.signIn({
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

  ngOnDestroy(): void {
    if (this.auth$ != undefined) {
      this.auth$.unsubscribe();
    }
  }

}
