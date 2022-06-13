import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Project, ProjectService, Roadmap, Task} from "../../shared/services/client";
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
        tap(p => this.roadmap = p.roadmap),
  ).subscribe()
  }

  public openTask(taskId?: number): void {
    this.router.navigateByUrl('/project/' + this.id + '/task/' + taskId)
  }

  public compare(a: Task, b: Task) {
      if ( a.status == 'LOCKED' ?  2 : a.status == 'TODO' ? 1 : 3 < (b.status == 'LOCKED' ?  2 : b.status == 'TODO' ? 1 : 3)){
        return -1;
      }
      if ( a.status == 'LOCKED' ?  2 : a.status == 'TODO' ? 1 : 3 > (b.status == 'LOCKED' ?  2 : b.status == 'TODO' ? 1 : 3)){
        return 1;
      }
      return 0;
  }
}
