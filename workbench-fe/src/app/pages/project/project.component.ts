import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Project, ProjectService, Roadmap} from "../../shared/services/client";
import {map, Observable, tap} from "rxjs";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";

@Component({
  selector: 'app-projects',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProjectComponent implements OnInit{

  project$: Observable<Project> = Observable.prototype;
  id?: number;
  roadmap?: Roadmap;
  svg: any;

  constructor(
      private readonly projectService: ProjectService,
      private route: ActivatedRoute,
      private readonly router: Router,
  ) {
  }


  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = parseInt(String(params.get('id')))
    })
    if (this.id != null) {
      this.project$ = this.projectService.getProjectById(this.id);
    }
    this.project$.pipe(
        tap(p => this.roadmap = p.roadmap)
    ).subscribe()
  }

  public openTask(taskId?: number): void {
    this.router.navigateByUrl('/project/' + this.id + '/task/' + taskId)
  }
}
