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
  post: Post;
  submitted = false;
  jsonVersion: JSON;

  constructor(private route: ActivatedRoute, private postTypeService: PostTypeService,
    private postService: PostService, private formBuilder: FormBuilder, private formAreaService: FormAreaService) { }

  createFormAreaInstanceAddForm(postType: PostType) {
    this.postType = postType;
    this.formAreaInstanceAddForm = this.formBuilder.group({
    });
    for (let j = 0; j < postType.formAreas.length; j++) {
      this.formAreaInstanceAddForm.addControl(postType.formAreas[j].label, new FormControl('', Validators.required));
    } 
  }

  ngOnInit() {
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

  add(){
    //console.log(Object.keys(this.formAreaInstanceAddForm.controls).length);

    if(this.formAreaInstanceAddForm.valid){
      let trialPost = {id: null, postText: JSON.stringify(this.formAreaInstanceAddForm.value), postTypeId: this.postType.id};
      this.post = Object.assign({},trialPost);
      console.log(JSON.parse(JSON.stringify(this.formAreaInstanceAddForm.value)).data[0]);
    }
    this.submitted = true;
    this.postService.createPost(this.postType.id, this.post)
      .subscribe(data => {
        this.post = data;
        this.newPost();
      }, error => console.log(error));
  }

  newPost(): void {
    this.submitted = false;
    this.post = new Post();
  }
}