import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { CommunitiesComponent } from './communities/communities.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import {HttpModule} from "@angular/http";
import { NavComponent } from './nav/nav.component';
import { CommunityService } from './community.service';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthService } from './auth.service';
import { AccountService } from './account.service';
import { UrlPermission } from './url.permission';

@NgModule({
  declarations: [
    AppComponent,
    CreateCommunityComponent,
    CommunityDetailsComponent,
    CommunitiesComponent,
    NavComponent,
    LoginComponent,
    LogoutComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    HttpModule
  ],

  providers: [CommunityService, CommunitiesComponent, CommunityDetailsComponent, CreateCommunityComponent, AuthService,AccountService,UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule { }
