import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { NewPostTypeService } from '../services/newPostType.service';
import { ActivatedRoute } from '@angular/router';
import { CommunityService } from '../services/community.service';
import { PostTypeService } from '../services/postType.service';
import { Community } from '../community';
import { FormArea } from '../formArea';
import { WikiData } from '../wikiData';

@Component({
  selector: 'app-newPostType.',
  templateUrl: './newPostType.component.html',
  styleUrls: ['./newPostType.component.css']
})
export class NewPostTypeComponent implements OnInit {

  postTypeId: number;
  submitted = false;
  saved = false;
  postTypeAddForm: FormGroup;
  postTypeFormArea: Array<FormArea>;
  postType: PostType
  community: Community;
  tagArray: Object;
  suggestionTags: WikiData[];
  tag:string;
  arr: string[];
  tags: WikiData[];
  importTag: WikiData;
  list = false;
  allTags: WikiData[];

  constructor(private route: ActivatedRoute, private communityService: CommunityService,
    private newPostTypeService: NewPostTypeService, private formBuilder: FormBuilder,
    private postTypeService: PostTypeService) { }

  createPostTypeAddForm() {
    this.postTypeAddForm = this.formBuilder.group({
      name: ["", Validators.required],
      usage: ["", Validators.required],
      semanticTag:["",Validators.required],
      selectedTags:["",Validators.required],
    });
  }

  ngOnInit() {
    this.allTags = new Array<WikiData>();
    this.submitted = false;
   this.createPostTypeAddForm();
   this.route.params.subscribe(params=>{
    this.communityService.getCommunityById(params.id).subscribe(data => this.community = data)
  });
  }
   
  save(){
    if(this.postTypeAddForm.valid){
      let trial = {id:null, name: this.postTypeAddForm.get("name").value, usage:this.postTypeAddForm.get("usage").value,
    semanticTag: this.postTypeAddForm.get("semanticTag").value, selectedTags: JSON.stringify(this.postTypeAddForm.get("selectedTags").value),
  communityId: null, formAreas: null, posts: null }
      this.postType = Object.assign({},trial);
      console.log(this.postType);
    }
    this.submitted = true;
    this.newPostTypeService.createPostType(this.postType, this.community.id)
      .subscribe(data => {
        this.postType.id = data['id'];
        this.newPostType();
      }, error => console.log(error));
  }

  newPostType(): void {
        this.saved = true;
  }

  tagSearch(){
    this.arr = this.postTypeAddForm.get("semanticTag").value.split(" ");
    this.arr.push(this.postTypeAddForm.get("semanticTag").value);
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