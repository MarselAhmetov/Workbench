import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';

@Component({
  selector: 'app-setting-card',
  templateUrl: './setting-card.component.html',
  styleUrls: ['./setting-card.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SettingCardComponent implements OnInit {

  @Input()
  title = '';

  constructor() { }

  ngOnInit(): void {
  }

}
