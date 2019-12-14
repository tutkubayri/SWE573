import { Component, OnInit } from '@angular/core';
import { PostType } from '../postType';
import { Observable } from 'rxjs';
import { PostTypeService } from '../services/postType.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-advanced',
  templateUrl: './advanced.component.html',
  styleUrls: ['./advanced.component.css']
})
export class AdvancedComponent implements OnInit {

  constructor(
    private postTypeService: PostTypeService,
    private activatedRoute: ActivatedRoute,
    ) { }
  postTypes: Observable<PostType[]>;

  ngOnInit() {
    this.activatedRoute.params.subscribe(params=>{
      this.postTypeService.getPostTypesList().subscribe(data => this.postTypes = data)
    });
  }
}