import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { CommunitiesListComponent } from './communitieslist/communitieslist.component';
import { SearchCommunitiesComponent } from './search-communities/search-communities.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { NavComponent } from './nav/nav.component';
import { CommunityService } from './community.service';
import { AddComponent } from './add/add.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateCommunityComponent,
    CommunityDetailsComponent,
    CommunitiesListComponent,
    SearchCommunitiesComponent,
    NavComponent,
    AddComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CommunityService, CommunitiesListComponent, CommunityDetailsComponent, CreateCommunityComponent],
  bootstrap: [AppComponent]
  
})
export class AppModule { }
