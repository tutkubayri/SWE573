import { Component, OnInit, Input } from '@angular/core';
import { CommunityService } from '../community.service';
import { Observable } from 'rxjs';
import { Community } from '../community';
import { Post } from '../post';
import { CommunitiesComponent } from '../communities/communities.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'community-details',
  templateUrl: './community-details.component.html',
  styleUrls: ['./community-details.component.css']
})
export class CommunityDetailsComponent implements OnInit {

  @Input() communityId: number;
  community: any;

  constructor(private route: ActivatedRoute, private communityService: CommunityService, private listComponent: CommunitiesComponent) { }

  ngOnInit() {
    this.route.params.subscribe(params=>{
      this.communityService.getCommunity(this.communityId).subscribe(data => this.community = data)
    });
  }

  getPostTypes(){
    
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

  deleteCustomer() {
    this.communityService.deleteCommunity(this.community.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
