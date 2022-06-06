import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {TaskItem, TaskService} from "../../shared/services/client";
import {Observable} from "rxjs";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-projects',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
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
    if (this.taskId != null) {
      this.task$ = this.taskService.getTaskById(this.taskId);
    }
  }
}
