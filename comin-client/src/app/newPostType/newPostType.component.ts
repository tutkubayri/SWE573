import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { NewPostTypeService } from '../services/newPostType.service';
import { ActivatedRoute } from '@angular/router';
import { CommunityService } from '../services/community.service';
import { PostTypeService } from '../services/postType.service';
import { Community } from '../community';
import { FormArea } from '../formArea';
import { Post } from '../post';

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

  constructor(private route: ActivatedRoute, private communityService: CommunityService,
    private newPostTypeService: NewPostTypeService, private formBuilder: FormBuilder,
    private postTypeService: PostTypeService) { }

  createPostTypeAddForm() {
    this.postTypeAddForm = this.formBuilder.group({
      name: ["", Validators.required],
      usage: ["", Validators.required],
    });
  }

  ngOnInit() {
   this.createPostTypeAddForm();
   this.route.params.subscribe(params=>{
    this.communityService.getCommunityById(params.id).subscribe(data => this.community = data)
  });
  }
   
  save(){
    if(this.postTypeAddForm.valid){
      this.postType = Object.assign({},this.postTypeAddForm.value)
    }
    this.submitted = true;
    this.newPostTypeService.createPostType(this.postType, this.community.id)
      .subscribe(data => {
        this.postType.id = data['id'];
        this.newPostType();
      }, error => console.log(error));
  }

  newPostType(): void {
    
        this.submitted = false;
        this.saved = true;
  }
}