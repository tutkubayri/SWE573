import { Component, OnInit } from '@angular/core';
import { CommunityService } from '../services/community.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Community } from '../community';
import { WikiData } from '../wikiData';
import { stringify } from 'querystring';
import { Router } from '@angular/router';
import { PostType } from '../postType';
import { NewPostTypeService } from '../services/newPostType.service';
import { FormArea } from '../formArea';
import { FormAreaService } from '../services/form-area.service';
import { timingSafeEqual } from 'crypto';

@Component({
  selector: 'create-community',
  templateUrl: './create-community.component.html',
  styleUrls: ['./create-community.component.css']
})
export class CreateCommunityComponent implements OnInit {

  submitted = false;

  constructor(private communityService: CommunityService, private formBuilder: FormBuilder, private router: Router,
    private postTypeService: NewPostTypeService, private formAreaService: FormAreaService) { }

  communityAddForm: FormGroup;
  community: Community = new Community();
  tagArray: Object;
  suggestionTags: WikiData[];
  importTag: WikiData;
  list = false;
  tag: string;
  arr: string[];
  tags: WikiData[];
  allTags: WikiData[];
  error = false;
  defaultPostType: PostType;
  formAreaName: FormArea;
  formAreaDescription: FormArea;
  redirect = false;

  createCommunityAddForm() {
    this.communityAddForm = this.formBuilder.group({
      name: ["", Validators.required],
      description: ["", Validators.required],
      semanticTag: ["", Validators.required],
      selectedTags: ["", Validators.required],
      //selectedQ:["",Validators.required], 
      bannerUrl: ["", Validators.required]
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.createCommunityAddForm();
    this.allTags = new Array<WikiData>();
  }

  add() {
    if (this.communityAddForm.valid) {
      let trialPost = {
        id: null, name: this.communityAddForm.get("name").value, description: this.communityAddForm.get("description").value,
        semanticTag: this.communityAddForm.get("semanticTag").value, selectedTags: JSON.stringify(this.communityAddForm.get("selectedTags").value),
        bannerUrl: this.communityAddForm.get("bannerUrl").value, postTypes: null
      };
      this.community = Object.assign({}, trialPost);
      this.error = false;
      this.submitted = true;
      this.communityService.createCommunity(this.community)
        .subscribe(data => {
          this.community = data['community'];
          let dPostType = { id: null, name: "Default Post Type", usage: "Default Post Type", communityId: null };
          this.postTypeService.createPostType(dPostType, data['id']).subscribe(data2 => {
            this.defaultPostType = data2['defaultPostType'];
            let dFormAreaName = { id: null, label: "Name", dataType: "text", requirement: "true", post_type_id: null };
            this.formAreaService.createFormArea(dFormAreaName, data2['id']).subscribe(data3 => {
              this.formAreaName = data3['formAreaName'];
              let dFormAreaDescription = { id: null, label: "Desciption", dataType: "text", requirement: "true", post_type_id: null };
              this.formAreaService.createFormArea(dFormAreaDescription, data2['id']).subscribe(data4 => {
                this.formAreaDescription = data4['formAreaDescription'];
              }, error => console.log(error));
            }, error => console.log(error));
          }, error => console.log(error));
        }, error => console.log(error));
        this.redirect = true;
      this.wait();
    }
    else {
      this.error = true;
    }
  }

  async wait() {
    await this.delay(3000);
    this.router.navigateByUrl('/communities');
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  tagSearch(){
    this.arr = this.communityAddForm.get("semanticTag").value.split(" ");
    this.arr.push(this.communityAddForm.get("semanticTag").value);
    for (let tagGroup of this.arr) {
      this.tagArray = null;
      this.communityService.tagSearch(tagGroup).subscribe(data => {
        this.tagArray = data;
        this.list = true;
        this.suggestionTags = new Array<WikiData>();
        for (let i = 0; i < Object.entries(this.tagArray)[1][1].length; i++) {
          this.suggestionTags[i] = Object.assign({});
          this.suggestionTags[i].label = Object.values(Object.entries(Object.entries(this.tagArray)[1])[1][1][i])[6].toString();
          this.suggestionTags[i].qcode = Object.values(Object.entries(Object.entries(this.tagArray)[1])[1][1][i])[1].toString();
          //console.log(JSON.stringify(this.suggestionTags[i]));
          //this.tags.push(this.suggestionTags[i]);
          //console.log(JSON.stringify(this.tags[i]));
        }
        this.tags = new Array<WikiData>();
        this.tags.push(this.suggestionTags[0]);
        let tagCount = 0;
        for (let j = 0; j < this.suggestionTags.length; j++) {
          tagCount = 0;
          for(let a = 0; a<this.tags.length; a++){
            if(this.suggestionTags[j].label != this.tags[a].label){
              tagCount = tagCount + 1;
            }
          }
          if (tagCount == this.tags.length) {
            this.tags.push(this.suggestionTags[j]);
          }
        } 
        //console.log(this.tags);
        this.all(this.tags);
        
      }, error => console.log(error));
    }
  }

  all(ts: Array<WikiData>){
    console.log(ts);
    let arrayWikiData = new Array<WikiData>();
    arrayWikiData = ts;
    for(let n = 1; n<arrayWikiData.length; n++){
      this.allTags.push(ts[n]);
    }
  }
}