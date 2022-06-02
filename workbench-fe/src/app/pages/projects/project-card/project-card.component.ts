import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';
import {Project} from "../../../shared/services/client";

@Component({
  selector: 's-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProjectCardComponent implements OnInit {

  @Input()
  progress = 15;

  @Input()
  project!: Project;

  constructor() { }

  ngOnInit(): void {
  }

}
