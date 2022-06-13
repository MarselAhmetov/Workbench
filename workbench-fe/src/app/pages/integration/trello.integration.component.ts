import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {IntegrationService} from "../../shared/services/client";
import {map, Subscription} from "rxjs";

@Component({
  selector: 'app-projects',
  templateUrl: './trello.integration.component.html'
})
export class TrelloIntegrationComponent implements OnInit {

  private integration$?: Subscription;


  constructor(
      private route: ActivatedRoute,
      private readonly router: Router,
      private integrationService: IntegrationService,
      ) {
  }

  ngOnInit() {
    this.route.fragment.pipe(map(value => {
      value ? this.sendToken(value.substring(6)) : ""
    }
    )).subscribe()
  }

  sendToken(token: string) {
    this.integration$ = this.integrationService.getAccessTrelloApi(
        {token: token}
    )
    .pipe(
        map(() => this.router.navigateByUrl('/projects')),
    ).subscribe();
  }
}
