import {Component, OnInit, ChangeDetectionStrategy, Inject,} from '@angular/core';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {

  subscribeSettings = {
    serviceNews: false,
    projectNotification: false,
    deadlineNotifications: false,
  }

  constructor(@Inject(DOCUMENT)private document: Document) { }

  ngOnInit(): void {
  }

  public googleOauth2() : void {
    this.document.location.href = 'https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=122652047502-1r58lea3jrmore7bvfbglfcdb3rb2ple.apps.googleusercontent.com&scope=https://www.googleapis.com/auth/drive.file&state=qZoIdH4uaIttBB10qSfk9OU64TOsYYIiELFEDCAXmc0%3D&redirect_uri=http://localhost:4200/login/oauth2/code/google&access_type=offline';
  }
}
