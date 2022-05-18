import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardModule } from "primeng/card";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { SignInComponent } from './pages/auth/sign-in/sign-in.component';
import { SignUpComponent } from './pages/auth/sign-up/sign-up.component';
import { IndexComponent } from './pages/index/index.component';
import { ToolbarModule } from "primeng/toolbar";
import { ButtonModule } from "primeng/button";
import { SplitButtonModule } from "primeng/splitbutton";
import { ButtonComponent } from './shared/components/button/button.component';
import { LogoComponent } from './shared/components/logo/logo.component';
import { InputTextModule } from "primeng/inputtext";
import { FormsModule } from "@angular/forms";
import { CheckboxModule } from "primeng/checkbox";
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import { MenuModule } from "primeng/menu";
import { ProjectsComponent } from './pages/projects/projects.component';
import { ProjectCardComponent } from './pages/projects/project-card/project-card.component';
import { NgxsModule } from "@ngxs/store";
import { environment } from "../environments/environment";
import { AppState } from "./shared/store/app-state.service";
import { TabViewModule } from "primeng/tabview";
import { KnobModule } from "primeng/knob";
import { SettingsComponent } from './pages/settings/settings.component';
import { SettingCardComponent } from './pages/settings/setting-card/setting-card.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    IndexComponent,
    ButtonComponent,
    LogoComponent,
    SidebarComponent,
    ProjectsComponent,
    ProjectCardComponent,
    SettingsComponent,
    SettingCardComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    ButtonModule,
    CardModule,
    CheckboxModule,
    FormsModule,
    HttpClientModule,
    InputTextModule,
    KnobModule,
    MenuModule,
    NgxsModule.forRoot([AppState], {
      developmentMode: !environment.production
    }) ,
    SplitButtonModule,
    TabViewModule,
    ToolbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
