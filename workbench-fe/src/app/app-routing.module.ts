import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from "./pages/index/index.component";
import { SignInComponent } from "./pages/auth/sign-in/sign-in.component";
import { SignUpComponent } from "./pages/auth/sign-up/sign-up.component";
import { ProjectsComponent } from "./pages/projects/projects.component";
import { SettingsComponent } from "./pages/settings/settings.component";
import { AuthGuard } from "./shared/services/auth.guard";
import { NonAuthGuard } from "./shared/services/non-auth.guard";
import {GoogleIntegrationComponent} from "./pages/integration/google.integration.component";
import {ProjectComponent} from "./pages/project/project.component";
import {TaskComponent} from "./pages/task/task.component";

const routes: Routes = [
  {
    path: '',
    component: IndexComponent,
    // canActivate: [NonAuthGuard],
  },
  {
    path: 'sign-in',
    component: SignInComponent,
    canActivate: [NonAuthGuard],
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    canActivate: [NonAuthGuard],
  },
  {
    path: 'projects',
    component: ProjectsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'settings',
    component: SettingsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'login/oauth2/code/google',
    component: GoogleIntegrationComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'project/:id',
    component: ProjectComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'project/:projectId/task/:taskId',
    component: TaskComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
