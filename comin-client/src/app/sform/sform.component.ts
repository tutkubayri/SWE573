import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Post } from '../post';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../services/post.service';
import { PostTypeService } from '../services/postType.service';
import { FormAreaService } from '../services/form-area.service';

@Component({
  selector: 'app-sform',
  templateUrl: './sform.component.html',
  styleUrls: ['./sform.component.css']
})

export class SformComponent implements OnInit {

  @Input() id: number;
  postType: PostType;
  formAreas: String [];
  formAreaInstanceAddForm: FormGroup;
  submitted = false;
  searchFields: JSON;
  values: string[];
  keys: string[];
  results: Post[];
  entries: String[];
  entries2: String[];

  constructor(private route: ActivatedRoute, private postTypeService: PostTypeService,
    private postService: PostService, private formBuilder: FormBuilder, private formAreaService: FormAreaService) { }

  createFormAreaInstanceAddForm(postType: PostType) {
    this.postType = postType;
    this.formAreaInstanceAddForm = this.formBuilder.group({
    });
    for (let j = 0; j < postType.formAreas.length; j++) {
      this.formAreaInstanceAddForm.addControl(postType.formAreas[j].label, new FormControl('', Validators.required));
    }
    this.formAreaInstanceAddForm.addControl("selectedTags", new FormControl('', Validators.required));
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

  search(){
    this.searchFields = Object.assign({}, this.formAreaInstanceAddForm.value);
    console.log(this.formAreaInstanceAddForm.value);
    this.keys = new Array<string>();
    this.values = new Array<string>();
    /* this.entries = new Array<string>();
    this.entries2 = new Array<string>(); */
    for(let i = 0; i<Object.values(this.searchFields).length; i++){
      if(Object.values(this.searchFields)[i] != ""){
        this.values.push(Object.values(this.searchFields)[i]);
      }
    }
    for(let j = 0; j<Object.keys(this.searchFields).length; j++){
      if(Object.values(this.searchFields)[j] != ""){
        this.keys.push(Object.keys(this.searchFields)[j]);
      }
    }

    /* for(let k = 0; k<this.values.length; k++){
      this.entries.push(this.keys[k] + "\":\"" + this.values[k]);
    } */

    this.postService.searchPost(this.keys, this.values)
    .subscribe(data => {
      this.results = data;
      console.log(this.results);
    }, error => console.log(error));
  }
}
