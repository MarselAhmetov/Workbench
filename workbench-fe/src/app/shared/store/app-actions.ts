import {Project, User} from "../services/client";

export class UserLoggedInAction {
  static readonly type = '[User] logged in'
  constructor(public user?: User) {}
}

export class UserLoggedOutAction {
  static readonly type = '[User] logged out'
}
