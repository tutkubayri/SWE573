import { Component, OnInit } from '@angular/core';
import { Community } from '../community';
import { CommunityService } from '../community.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
    this.createCommunityAddForm();
  }

  add(){
    if(this.communityAddForm.valid){
      this.community = Object.assign({},this.communityAddForm.value)
    }
    this.submitted = true;
    this.communityService.createCommunity(this.community)
      .subscribe(data => console.log(data), error => console.log(error));
    this.community = new Community();
    this.newCommunity();
  }

  newCommunity(): void {
    this.submitted = false;
    this.community = new Community();
  }
}