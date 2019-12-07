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
  formAreas: Observable<FormArea[]>;
  formAreaInstanceAddForm: FormGroup;
  post: Post;
  submitted = false;
  createdFormAreas: FormArray;

  constructor(private route: ActivatedRoute, private postTypeService: PostTypeService,
    private postService: PostService, private formBuilder: FormBuilder, private formAreaService: FormAreaService) { }

  createFormAreaInstanceAddForm() {
    this.formAreaInstanceAddForm = this.formBuilder.group({

    });
   /*  for (let j = 0; j < this.formAreas.length; j++) {
      this.formAreaInstanceAddForm.addControl("this.formAreas[j].label", new FormControl('', Validators.required));
    } */
  }

  ngOnInit() {
    this.route.params.subscribe(params=>{
      this.postTypeService.getPostTypeById(params.id).subscribe(data => 
        this.postType = data)
        this.formAreaService.getFormAreasByPostTypeId(this.postType.id).subscribe(data2 =>
          this.formAreas = data2)
      });
    this.createFormAreaInstanceAddForm();
  }

  getFormAreas(postTypeId:number){
    this.formAreaService.getFormAreasByPostTypeId(postTypeId)
      .subscribe(data => {
        this.formAreas = data;
      }, error => console.log(error));
  }

  /* add(){
    if(this.formAreaInstanceAddForm.valid){
      if(this.formAreaInstanceAddForm.get("filled").value == String){
        this.post.texts.push(this.formAreaInstanceAddForm.get("filled").value);
      }
      if(this.formAreaInstanceAddForm.get("filled").value == Number){
        this.post.integers.push(this.formAreaInstanceAddForm.get("filled").value);
      }
      this.post = Object.assign({},this.formAreaInstanceAddForm.value)
    }
    this.submitted = true;
    this.postService.createPost(this.post, this.id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.post = new Post();
    this.newPost();
  }

  newPost(): void {
    this.submitted = false;
    this.post = new Post();
  } */
}