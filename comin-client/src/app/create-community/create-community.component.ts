import { Component, OnInit } from '@angular/core';
import { CommunityService } from '../services/community.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Community } from '../community';
import { WikiData } from '../wikiData';
import { stringify } from 'querystring';

@Component({
  selector: 'create-community',
  templateUrl: './create-community.component.html',
  styleUrls: ['./create-community.component.css']
})
export class CreateCommunityComponent implements OnInit {

  submitted = false;

  constructor(private communityService: CommunityService, private formBuilder:FormBuilder) { }

  communityAddForm:FormGroup;
  community: Community = new Community();
  tagArray: Object;
  suggestionTags: WikiData[];
  importTag: WikiData;
  list = false;
  tag:string;
  arr: string[];
  tags: WikiData[];

  createCommunityAddForm(){
    this.communityAddForm = this.formBuilder.group({
      name:["",Validators.required],
      description:["",Validators.required],
      semanticTag:["",Validators.required],
      selectedTags:["",Validators.required],
      //selectedQ:["",Validators.required], 
      bannerUrl:["",Validators.required]
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.createCommunityAddForm();
  }

  add(){
    if(this.communityAddForm.valid){
      let trialPost = {id:null, name:this.communityAddForm.get("name").value, description:this.communityAddForm.get("description").value,
      semanticTag: this.communityAddForm.get("semanticTag").value, selectedTags: JSON.stringify(this.communityAddForm.get("selectedTags").value),
      bannerUrl: this.communityAddForm.get("bannerUrl").value, postTypes: null};
      this.community = Object.assign({}, trialPost);
     }
    this.submitted = true;
    this.communityService.createCommunity(this.community)
  .subscribe(data => {
    this.community = data['community'];
  }, error => console.log(error));
  }

  tagSearch(){
    this.arr = this.communityAddForm.get("semanticTag").value.split(" ");
    this.arr.push(this.communityAddForm.get("semanticTag").value);
    for(let tagGroup of this.arr){
      this.tagArray = null;
      this.communityService.tagSearch(tagGroup).subscribe(data => {this.tagArray = data;
      this.list = true;
      this.suggestionTags = new Array<WikiData>();
      this.tags = new Array<WikiData>();
      for(let i=0; i<Object.entries(this.tagArray)[1][1].length; i++){        
        this.suggestionTags[i] = Object.assign({});
        this.suggestionTags[i].label = Object.values(Object.entries(Object.entries(this.tagArray)[1])[1][1][i])[6].toString();
        this.suggestionTags[i].qcode = Object.values(Object.entries(Object.entries(this.tagArray)[1])[1][1][i])[1].toString();
        //console.log(JSON.stringify(this.suggestionTags[i]));
        this.tags.push(this.suggestionTags[i]);
        //console.log(JSON.stringify(this.tags[i]));
      }}, error => console.log(error));
    }
  }
}