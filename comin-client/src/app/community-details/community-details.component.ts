import { Component, OnInit, Input } from '@angular/core';
import { CommunityService } from '../services/community.service';
import { CommunitiesComponent } from '../communities/communities.component';
import { ActivatedRoute } from '@angular/router';
import { PostType } from '../postType';
import { Observable } from 'rxjs';
import { PostTypeService } from '../services/postType.service';
import { Community } from '../community';

@Component({
  selector: 'community-details',
  templateUrl: './community-details.component.html',
  styleUrls: ['./community-details.component.css']
})
export class CommunityDetailsComponent implements OnInit {

  @Input() id: number;
  community: Community;
  postTypes: Observable<PostType[]>;

  constructor(private route: ActivatedRoute, 
    private communityService: CommunityService, 
    private listComponent: CommunitiesComponent) { }

  ngOnInit() {
    this.route.params.subscribe(params=>{
      this.communityService.getCommunityById(params.id).subscribe(data => this.community = data)
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
