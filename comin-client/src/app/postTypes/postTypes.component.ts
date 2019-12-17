import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { ActivatedRoute } from '@angular/router';
import { PostTypeService } from '../services/postType.service';
import { PostService } from '../services/post.service';
import { FormGroup, Validators, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { FormArea } from '../formArea';
import { Observable } from 'rxjs';
import { Post } from '../post';
import { FormAreaService } from '../services/form-area.service';
import { WikiData } from '../wikiData';

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

  constructor(private route: ActivatedRoute, private postTypeService: PostTypeService,
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
      this.formAreaInstanceAddForm.addControl(postType.formAreas[j].label, new FormControl('', Validators.required));
    } 
  }

  ngOnInit() {
    this.submitted = false;
    this.route.params.subscribe(params=>{
      this.postTypeService.getPostTypeById(params.id).subscribe(data => this.createFormAreaInstanceAddForm(data));
      });
    
  }

  getFormAreas(postTypeId:number){
    this.formAreaService.getFormAreasByPostTypeId(postTypeId)
      .subscribe(data => {
        this.formAreas = data;
      }, error => console.log(error));
  }

  tagSearch(){
    this.arr = this.tagForm.get("semanticTag").value.split(" ");
    this.arr.push(this.tagForm.get("semanticTag").value);
    for(let tagGroup of this.arr){
      this.tagArray = null;
      this.postTypeService.tagSearch(tagGroup).subscribe(data => {this.tagArray = data;
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

  add(){
    if(this.formAreaInstanceAddForm.valid){
      let trialPost = {id: null, postText: JSON.stringify(this.formAreaInstanceAddForm.value), postTypeId: this.postType.id,
      semanticTag: JSON.stringify(this.tagForm.get("semanticTag").value), selectedTags: JSON.stringify(this.tagForm.get("selectedTags").value)};
      this.post = Object.assign({},trialPost);
    }
    this.submitted = true;
    this.postService.createPost(this.postType.id, this.post)
      .subscribe(data => {
        this.post = data;
        this.newPost();
      }, error => console.log(error));
  }

  newPost(): void {
    this.post = new Post();
  }
}