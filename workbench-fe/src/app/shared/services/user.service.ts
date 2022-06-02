import { Injectable } from '@angular/core';
import { SignUpResponse } from "./client";
import { Router } from "@angular/router";
import { Store } from "@ngxs/store";
import { flatMap, from, Observable, take, tap } from "rxjs";
import { UserLoggedOutAction } from "../store/app-actions";

const TOKEN_KEY = 'AUTH_TOKEN';

export const isAuthenticated = () => !!localStorage.getItem(TOKEN_KEY);

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
      private readonly router: Router,
      private readonly store: Store,
  ) {
  }

  saveToLocalStorage(response: SignUpResponse) {
    localStorage.setItem(TOKEN_KEY, response.token || '');
  }

  logout() {
    from(this.router.navigateByUrl('/')).pipe(
        take(1),
        tap(() => localStorage.removeItem(TOKEN_KEY)),
        flatMap(() => this.store.dispatch(new UserLoggedOutAction()))
    ).subscribe();
  }

  static isAuthenticated(): boolean {
    const res = !!localStorage.getItem(TOKEN_KEY);
    // console.log(`isAuthenticated called, value is ${res}`)
    return res;
  }

  static getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }
}
