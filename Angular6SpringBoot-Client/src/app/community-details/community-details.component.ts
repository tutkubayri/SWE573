import { Component, OnInit, Input } from '@angular/core';
import { CommunityService } from '../community.service';
import { Community } from '../community';
import { CommunitiesListComponent } from '../communitieslist/communitieslist.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'community-details',
  templateUrl: './community-details.component.html',
  styleUrls: ['./community-details.component.css']
})
export class CommunityDetailsComponent implements OnInit {

  @Input() community: Community;

  constructor(private route: ActivatedRoute, private communityService: CommunityService, private listComponent: CommunitiesListComponent) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
	
      this.communityService.getCommunity(+params.get('id')).subscribe(c =>{
       console.log(c);
       })   
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
