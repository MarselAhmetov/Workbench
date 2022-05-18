import { Injectable } from '@angular/core';
import {AuthService, SignInRequest, SignUpResponse} from "./client";

const TOKEN_KEY = 'AUTH_TOKEN';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() {
  }

  saveToLocalStorage(response: SignUpResponse) {
    localStorage.setItem(TOKEN_KEY, response.token || '');
  }

  logout() {
    localStorage.removeItem(TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem(TOKEN_KEY);
  }
}
