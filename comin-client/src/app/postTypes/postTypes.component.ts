import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { ActivatedRoute } from '@angular/router';
import { PostTypeService } from '../postType.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { FormArea } from '../formArea';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-postTypes',
  templateUrl: './postTypes.component.html',
  styleUrls: ['./postTypes.component.css']
})
export class PostTypesComponent implements OnInit {

  @Input() id: number;
  postType: PostType;
  formAreas: Observable<FormArea[]>;
  formAreaInstanceAddForm:FormGroup;

  constructor(private route: ActivatedRoute, 
    private postTypeService: PostTypeService, private formBuilder:FormBuilder) { }

  createFormAreaInstanceAddForm(){
      this.formAreaInstanceAddForm = this.formBuilder.group({
        filled:[""],
      });
    }

  ngOnInit() {
    this.route.params.subscribe(params=>{
      this.postTypeService.getPostTypeById(params.id).subscribe(data => this.postType = data)
    });
    this.createFormAreaInstanceAddForm();
  }

  /* add(){
    if(this.formAreaInstanceAddForm.valid){
      this.community = Object.assign({},this.communityAddForm.value)
    }
  } */
}