import {Component, OnInit, ChangeDetectionStrategy, OnDestroy} from '@angular/core';
import {Project, ProjectService} from "../../shared/services/client";
import {Subscription, tap} from "rxjs";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProjectsComponent implements OnInit {

  private project$?: Subscription;

  projects: Project[] = [];

  constructor(
      private readonly projectService: ProjectService,
  ) {
  }

  getProjects() {
    this.project$ = this.projectService.getAllProjectsByUserId().pipe(
        tap(resp => this.projects = resp),
    ).subscribe();
  }

  ngOnInit() {
    this.getProjects()
  }
}
