import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommunitiesComponent } from './communities/communities.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProfileComponent } from './profile/profile.component';
import { UrlPermission } from './url.permission';

const routes: Routes = [
    { path: '', redirectTo: 'communities', pathMatch: 'prefix' },
    { path: 'id/:id', component:  CommunityDetailsComponent},
    { path: 'communities', component: CommunitiesComponent},
    { path: 'add', component: CreateCommunityComponent },
    { path: 'login', component: LoginComponent },
    { path: 'logout', component: LogoutComponent},
    { path: 'profile', component: ProfileComponent ,canActivate: [UrlPermission] },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }