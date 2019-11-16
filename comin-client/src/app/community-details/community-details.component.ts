import { Component, OnInit, Input } from '@angular/core';
import { CommunityService } from '../community.service';
import { Community } from '../community';
import { CommunitiesComponent } from '../communities/communities.component';
import { ActivatedRoute } from '@angular/router';
import { Post } from '../post';
import { Observable } from 'rxjs';
import { PostTypeService } from '../post.service';

@Component({
  selector: 'community-details',
  templateUrl: './community-details.component.html',
  styleUrls: ['./community-details.component.css']
})
export class CommunityDetailsComponent implements OnInit {

  @Input() communityId: number;
  community: Community;
  postTypes: Observable<Post[]>;

  constructor(private route: ActivatedRoute, 
    private communityService: CommunityService, 
    private listComponent: CommunitiesComponent,
    private postService: PostTypeService) { }

  ngOnInit() {
    this.route.params.subscribe(params=>{
      this.communityService.getCommunityById(this.communityId).subscribe(data => this.community = data)
    });
    this.route.params.subscribe(params=>{
      this.postService.getPostTypes(this.communityId).subscribe(data => this.postTypes = data)
    });
  }

  updateActive(isActive: boolean) {
    this.communityService.updateCommunity(this.community.id,
      { name: this.community.name, description: this.community.description, semanticTag: this.community.semanticTag, bannerUrl: this.community.bannerUrl })
      .subscribe(
        data => {
          console.log(data);
          this.community = data as Community;
        },
        error => console.log(error));
  }

  deleteCommunity() {
    this.communityService.deleteCommunity(this.community.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
