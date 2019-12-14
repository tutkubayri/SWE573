import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommunitiesComponent } from './communities/communities.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { PostTypesComponent } from './postTypes/postTypes.component';
import { NewPostTypeComponent } from './newPostType/newPostType.component';
import { FormAreaComponent } from './form-area/form-area.component';
import { AdvancedComponent } from './advanced/advanced.component';
import { SformComponent } from './sform/sform.component';

const routes: Routes = [
    { path: '', redirectTo: 'communities', pathMatch: 'prefix' },
    { path: 'communities/id/:id', component:  CommunityDetailsComponent},
    { path: 'communities', component: CommunitiesComponent},
    { path: 'communities/add', component: CreateCommunityComponent },
    { path: 'postTypes/add/:id', component: NewPostTypeComponent},
    { path: 'formAreas/add/:id', component: FormAreaComponent},
    { path: 'posts/add/:id', component: PostTypesComponent},
    { path: 'advanced', component: AdvancedComponent},
    { path: 'search/:id', component: SformComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }