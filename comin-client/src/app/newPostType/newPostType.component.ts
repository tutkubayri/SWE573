import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { NewPostTypeService } from '../services/newPostType.service';
import { ActivatedRoute } from '@angular/router';
import { CommunityService } from '../services/community.service';
import { PostTypeService } from '../services/postType.service';
import { Community } from '../community';

@Component({
  selector: 'app-newPostType.',
  templateUrl: './newPostType.component.html',
  styleUrls: ['./newPostType.component.css']
})
export class NewPostTypeComponent implements OnInit {

  submitted = false;
  postTypeAddForm: FormGroup;
  formAreas: FormArray;
  postType: PostType
  @Input() id: number;
  community: Community;

  constructor(private route: ActivatedRoute, private communityService: CommunityService,
    private newPostTypeService: NewPostTypeService, private formBuilder: FormBuilder) { }

  createPostTypeAddForm() {
    this.postTypeAddForm = this.formBuilder.group({
      name: ["", Validators.required],
      usage: ["", Validators.required],
      formAreas: this.formBuilder.array([
        this.initFormArea(),
      ])
    });
  }

  addFormArea():void{
    this.formAreas = this.postTypeAddForm.get('formAreas') as FormArray;
    this.formAreas.push(this.initFormArea());
  }

  initFormArea():FormGroup{
    return this.formBuilder.group({
      label: ['', Validators.required],
      dataType: ['', Validators.required],
      isRequired: ['', Validators.required]
    });
  }
  ngOnInit() {
   this.createPostTypeAddForm();
   this.route.params.subscribe(params=>{
    this.communityService.getCommunityById(params.id).subscribe(data => this.community = data)
  });
  }

  add(){
    if(this.postTypeAddForm.valid){
      this.postType = Object.assign({},this.postTypeAddForm.value)
    }
    this.submitted = true;
    this.newPostTypeService.createPostType(this.postType, this.community.id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.postType = new PostType();
    this.newPostType();
  }

  newPostType(): void {
    this.submitted = false;
    this.postType = new PostType();
  }
}
