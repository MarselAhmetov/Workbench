import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Project, ProjectService} from "../../shared/services/client";
import {map, Observable, of} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProjectsComponent implements OnInit {

  project$: Observable<Project[]> = of([]);

  constructor(
      private readonly projectService: ProjectService,
      private readonly router: Router,
  ) {
  }

  ngOnInit() {
    this.project$ = this.projectService.getAllProjectsByUserId();
  }

  public openProject(id?: number): void {
    this.router.navigateByUrl('/project/' + id)
  }
}
