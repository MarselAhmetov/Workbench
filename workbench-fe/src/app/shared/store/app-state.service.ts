import { Injectable } from '@angular/core';
import { State } from "@ngxs/store";


export interface AppStateModel {
  currentPage: string;
  isAuthenticated: boolean;
}

@State<AppStateModel>({
  name: 'app',
  defaults: {
    currentPage: 'Проекты',
    isAuthenticated: false
  }
})
@Injectable({
  providedIn: 'root'
})
export class AppState {

  constructor() { }
}
