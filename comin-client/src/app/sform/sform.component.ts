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
  post: Post;
  submitted = false;
  searchFields: JSON;

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
    console.log(this.searchFields);
  }
}
