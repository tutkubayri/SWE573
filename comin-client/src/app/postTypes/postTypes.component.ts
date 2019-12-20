import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { ActivatedRoute, Router, RoutesRecognized } from '@angular/router';
import { PostTypeService } from '../services/postType.service';
import { PostService } from '../services/post.service';
import { FormGroup, Validators, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { FormArea } from '../formArea';
import { Observable } from 'rxjs';
import { Post } from '../post';
import { FormAreaService } from '../services/form-area.service';
import { WikiData } from '../wikiData';
import { Community } from '../community';
import { filter, pairwise } from 'rxjs/operators';
import { Enum } from '../enum';

@Component({
  selector: 'app-postTypes',
  templateUrl: './postTypes.component.html',
  styleUrls: ['./postTypes.component.css']
})
export class PostTypesComponent implements OnInit {

  @Input() id: number;
  postType: PostType;
  formAreas: String [];
  formAreaInstanceAddForm: FormGroup;
  tagForm: FormGroup;
  post: Post;
  submitted = false;
  jsonVersion: JSON;
  tagArray: Object;
  suggestionTags: WikiData[];
  tag:string;
  arr: string[];
  tags: WikiData[];
  importTag: WikiData;
  list = false;
  error = false;
  redirect = false;
  communityId: number;
  enumArray: Array<Enum>;
  enumValueArray: Array<string>;
  valueSplitArray: Array<Array<string>>;
  labelOfEnums: Array<string>;
  allTags: WikiData[];

  constructor(private route: ActivatedRoute, private postTypeService: PostTypeService, private router: Router,
    private postService: PostService, private formBuilder: FormBuilder, private formAreaService: FormAreaService) { }

  createFormAreaInstanceAddForm(postType: PostType) {
    this.postType = postType;
    this.tagForm = this.formBuilder.group({
      semanticTag:["",Validators.required],
      selectedTags:["",Validators.required],
    })
    this.formAreaInstanceAddForm = this.formBuilder.group({
    });
    for (let j = 0; j < postType.formAreas.length; j++) {
      if(postType.formAreas[j].requirement == true){
        this.formAreaInstanceAddForm.addControl(postType.formAreas[j].label, new FormControl('', Validators.required));
      }
      else{
        this.formAreaInstanceAddForm.addControl(postType.formAreas[j].label, new FormControl());
      }
    }
    this.getEnums(postType.formAreas);
  }

  ngOnInit() {
    this.submitted = false;
    this.route.params.subscribe(params=>{
      this.postTypeService.getPostTypeById(params.id).subscribe(
        data => this.createFormAreaInstanceAddForm(data));
      });
  }

  getEnums(formAreas: Array<FormArea>){
    this.enumArray = new Array<Enum>();
    this.labelOfEnums = new Array<string>();
    for(let a = 0; a<formAreas.length; a++){
      if(formAreas[a].dataType == "enum"){
        this.labelOfEnums.push(formAreas[a].label);
        this.formAreaService.getEnumByFormAreaId(formAreas[a].id)
        .subscribe(data => {
          this.enumArray.push(data);
          this.getEnumValues(this.enumArray);
          }
        , error => console.log(error))
      }
    }
  }

  getEnumValues(e: Array<Enum>){
    this.enumValueArray = new Array<string>();
    this.valueSplitArray = new Array<Array<string>>();
    for(let i = 0; i<e.length; i++){
      this.enumValueArray.push(e[i][0].value);
    }
    for(let j = 0; j<this.enumValueArray.length; j++){
      this.valueSplitArray.push(this.enumValueArray[j].split(';'));
    }
  }

  tagSearch(){
    this.arr = this.formAreaInstanceAddForm.get("semanticTag").value.split(" ");
    this.arr.push(this.formAreaInstanceAddForm.get("semanticTag").value);
    for (let tagGroup of this.arr) {
      this.tagArray = null;
      this.postTypeService.tagSearch(tagGroup).subscribe(data => {
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

  add(){
    if(this.formAreaInstanceAddForm.valid){
      let trialPost = {id: null, postText: JSON.stringify(this.formAreaInstanceAddForm.value), postTypeId: this.postType.id,
      semanticTag: JSON.stringify(this.tagForm.get("semanticTag").value), selectedTags: JSON.stringify(this.tagForm.get("selectedTags").value)};
      this.post = Object.assign({},trialPost);
      this.error = false;
      this.submitted = true;
      this.postService.createPost(this.postType.id, this.post)
        .subscribe(data => {
        this.post = data;
        this.newPost();
        this.redirect = true;
        this.wait()
      }, error => console.log(error));
    }
      else{
        this.error = true;
      }
  }

  newPost(): void {
    this.post = new Post();
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  async wait() {
      await this.delay(3000);
      this.redirect = false;
      this.router.navigateByUrl('/communities');
    }
}