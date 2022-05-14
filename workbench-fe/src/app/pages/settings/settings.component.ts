import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SettingsComponent implements OnInit {

  subscribeSettings = {
    serviceNews: false,
    projectNotification: false,
    deadlineNotifications: false,
  }

  constructor() { }

  ngOnInit(): void {
  }

}
