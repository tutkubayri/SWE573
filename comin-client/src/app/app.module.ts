import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { CommunitiesComponent } from './communities/communities.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { NavComponent } from './nav/nav.component';
import { CommunityService } from './community.service';

@NgModule({
  declarations: [
    AppComponent,
    CreateCommunityComponent,
    CommunityDetailsComponent,
    CommunitiesComponent,
    NavComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CommunityService, CommunitiesComponent, CommunityDetailsComponent, CreateCommunityComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
