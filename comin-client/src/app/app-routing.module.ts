import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommunitiesComponent } from './communities/communities.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { PostTypesComponent } from './postTypes/postTypes.component';

const routes: Routes = [
    { path: '', redirectTo: 'communities', pathMatch: 'prefix' },
    { path: 'communities/id/:id', component:  CommunityDetailsComponent},
    { path: 'communities', component: CommunitiesComponent},
    { path: 'postTypes/:id', component: PostTypesComponent},
    { path: 'communities/add', component: CreateCommunityComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }