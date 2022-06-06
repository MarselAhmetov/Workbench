import {Injectable} from '@angular/core';
import {Action, State, StateContext} from "@ngxs/store";
import {isAuthenticated, userFromLocalStorage} from "../services/user.service";
import {UserLoggedInAction, UserLoggedOutAction} from "./app-actions";
import {Project, User} from "../services/client";

// import { UserLoggedInAction, UserLoggedOutAction } from "./app-actions";


export interface AppStateModel {
  currentPage: string;
  isAuthenticated: boolean;
  user?: User,
  projects: Project[],
}

@State<AppStateModel>({
  name: 'app',
  defaults: {
    currentPage: 'Проекты',
    isAuthenticated: isAuthenticated(),
    user: userFromLocalStorage(),
    projects: []
  }
})
@Injectable({
  providedIn: 'root'
})
export class AppState {

  constructor() { }

  @Action(UserLoggedInAction)
  userLoggedIn(ctx: StateContext<AppStateModel>, action: UserLoggedInAction) {
    const state = ctx.getState();
    ctx.setState({
      ...state,
      user: action.user,
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
