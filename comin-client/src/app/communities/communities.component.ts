import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CommunityService } from '../services/community.service';
import { ActivatedRoute } from '@angular/router';
import { Community } from '../community';

@Component({
  selector: 'communities',
  templateUrl: './communities.component.html',
  styleUrls: ['./communities.component.css']
})
export class CommunitiesComponent implements OnInit {

  communities: Observable<Community[]>;

  constructor(
    private communityService: CommunityService,
    private activatedRoute: ActivatedRoute,
    ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params=>{
      this.communityService.getCommunitiesList().subscribe(data => this.communities = data)
    });
  }

  deleteCommunities() {
    this.communityService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.communities = this.communityService.getCommunitiesList();
  }
}