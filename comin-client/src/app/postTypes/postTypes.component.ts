import { Component, OnInit, Input } from '@angular/core';
import { PostType } from '../postType';
import { ActivatedRoute } from '@angular/router';
import { PostTypeService } from '../services/postType.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { FormArea } from '../formArea';
import { Observable } from 'rxjs';
import { Integer } from '../Integer';
//import { TextService } from '../text.service';

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
  Text: Text;
  Integer: Integer;
  submitted = false;

  constructor(private route: ActivatedRoute, //private textService: TextService,
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

 /*  add(){
    if(this.formAreaInstanceAddForm.valid){
      if(this.formAreaInstanceAddForm.get("filled").value == String){
        this.Text = Object.assign({},this.formAreaInstanceAddForm.value);
      }
    }
    this.submitted = true;
    this.textService.createCommunity(this.community)
      .subscribe(data => console.log(data), error => console.log(error));
    this.community = new Community();
    this.newCommunity();
  } */
}