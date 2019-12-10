import { Component, OnInit } from '@angular/core';
import { CommunityService } from '../services/community.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Community } from '../community';

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

  createCommunityAddForm(){
    this.communityAddForm = this.formBuilder.group({
      name:["",Validators.required],
      description:["",Validators.required],
      semanticTag:["",Validators.required],
      bannerUrl:["",Validators.required]
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.createCommunityAddForm();
  }

  add(){
    if(this.communityAddForm.valid){
      this.community = Object.assign({},this.communityAddForm.value)
      console.log(this.communityAddForm.value);
    }
    this.submitted = true;
    this.communityService.createCommunity(this.community)
  .subscribe(data => {
    this.community = data['community'];
  }, error => console.log(error));
  }
}