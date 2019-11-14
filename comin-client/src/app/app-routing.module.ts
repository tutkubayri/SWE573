import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommunitiesComponent } from './communities/communities.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';

const routes: Routes = [
    { path: '', redirectTo: 'communities', pathMatch: 'prefix' },
    { path: 'id/:id', component:  CommunityDetailsComponent},
    { path: 'communities', component: CommunitiesComponent },
    { path: 'add', component: CreateCommunityComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }