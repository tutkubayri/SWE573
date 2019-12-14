import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { CreateCommunityComponent } from './create-community/create-community.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { CommunitiesComponent } from './communities/communities.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule} from "@angular/http";
import { NavComponent } from './nav/nav.component';
import { PostTypesComponent } from './postTypes/postTypes.component';
import { NewPostTypeComponent } from './newPostType/newPostType.component';
import { CommunityService } from './services/community.service';
import { FormAreaComponent } from './form-area/form-area.component';
import { AdvancedComponent } from './advanced/advanced.component';
import { SformComponent } from './sform/sform.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateCommunityComponent,
    CommunityDetailsComponent,
    CommunitiesComponent,
    NavComponent,
    PostTypesComponent,
    NewPostTypeComponent,
    FormAreaComponent,
    AdvancedComponent,
    SformComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    HttpModule,
  ],

  providers: [CommunityService, CommunitiesComponent, CommunityDetailsComponent, CreateCommunityComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
