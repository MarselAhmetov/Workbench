import { Injectable } from '@angular/core';
import { Action, State, StateContext } from "@ngxs/store";
import { isAuthenticated } from "../services/user.service";
import { UserLoggedInAction, UserLoggedOutAction } from "./app-actions";
// import { UserLoggedInAction, UserLoggedOutAction } from "./app-actions";


export interface AppStateModel {
  currentPage: string;
  isAuthenticated: boolean;
}

@State<AppStateModel>({
  name: 'app',
  defaults: {
    currentPage: 'Проекты',
    isAuthenticated: isAuthenticated(),
  }
})
@Injectable({
  providedIn: 'root'
})
export class AppState {

  constructor() { }

  @Action(UserLoggedInAction)
  userLoggedIn(ctx: StateContext<AppStateModel>) {
    const state = ctx.getState();
    ctx.setState({
      ...state,
      isAuthenticated: true
    });
  }
  @Action (UserLoggedOutAction)
  userLoggedOut(ctx: StateContext<AppStateModel>) {
    const state = ctx.getState();
    ctx.setState({
      ...state,
      isAuthenticated: false
    });
  }
}
