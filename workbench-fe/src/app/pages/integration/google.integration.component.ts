import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {IntegrationService} from "../../shared/services/client";
import {map, Subscription} from "rxjs";

@Component({
  selector: 'app-projects',
  templateUrl: './google.integration.component.html'
})
export class GoogleIntegrationComponent implements OnInit {

  private integration$?: Subscription;


  constructor(
      private route: ActivatedRoute,
      private readonly router: Router,
      private integrationService: IntegrationService,
      ) {
  }

  ngOnInit() {
    this.route.queryParams
    .subscribe(params => {
          console.log(params);
          let code = params['code'];
          this.sendCode(code)
        }
    );

  }

  sendCode(code: string) {
    this.integration$ = this.integrationService.getAccessGoogleDriveApi(
        {code: code}
    )
    .pipe(
        map(() => this.router.navigateByUrl('/projects')),
    ).subscribe();
  }
}
