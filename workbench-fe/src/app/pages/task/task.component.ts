import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {TaskItem, TaskService} from "../../shared/services/client";
import {map, Observable, tap} from "rxjs";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-projects',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit{

  task$: Observable<TaskItem> = Observable.prototype;
  taskId?: number;

  constructor(
      private readonly taskService: TaskService,
      private route: ActivatedRoute,
  ) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.taskId = parseInt(String(params.get('taskId')))
    })
    this.getTask()
  }

  public getTemplate() {
    this.task$.pipe(
        map(task => {
          this.taskService.uploadTemplateToDrive(task).pipe()
          .subscribe()
        }),
        tap(() => this.getTask())
    ).subscribe()
  }

  public getTask() {
    if (this.taskId != null) {
      this.task$ = this.taskService.getTaskById(this.taskId);
    }
  }

  public validateDocument() {
    this.task$.pipe(
        map(task => {
          this.taskService.validateTaskDocumentFromDrive(task).pipe(
              map(() => this.getTask())
          ).subscribe()
        }),
    ).subscribe()
  }
}
