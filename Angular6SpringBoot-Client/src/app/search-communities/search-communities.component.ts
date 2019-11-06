import { Component, OnInit } from '@angular/core';
import { Community } from '../community';
import { CommunityService } from '../community.service';

@Component({
  selector: 'search-communities',
  templateUrl: './search-communities.component.html',
  styleUrls: ['./search-communities.component.css']
})
export class SearchCommunitiesComponent implements OnInit {

  name: string;
  communities: Community[];

  constructor(private dataService: CommunityService) { }

  ngOnInit() {
    this.name = "";
  }

  private searchCommunities() {
    this.dataService.getCommunitiesByName(this.name)
      .subscribe(communities => this.communities = communities);
  }

  onSubmit() {
    this.searchCommunities();
  }
}