import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { FormArea } from '../formArea';
import { FormAreaService } from '../services/form-area.service';
import { PostType } from '../postType';
import { ActivatedRoute } from '@angular/router';
import { PostTypeService } from '../services/postType.service';

@Component({
  selector: 'app-form-area',
  templateUrl: './form-area.component.html',
  styleUrls: ['./form-area.component.css']
})
export class FormAreaComponent implements OnInit {

  @Input() id: number;
  submitted = false;
  formAddForm: FormGroup;
  formAreas: FormArray;
  formArea: FormArea;
  postType: PostType;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private formAreaService: FormAreaService,
    private postTypeService: PostTypeService) { }

  ngOnInit() {
    this.submitted = false;
    this.route.params.subscribe(params => {
      this.postTypeService.getPostTypeById(params.id).subscribe(data => this.postType = data)
    });
    this.createFormAddForm();
  }

  addFormArea(): void {
    this.formAreas = this.formAddForm.get('formAreas') as FormArray;
    this.formAreas.push(this.initFormArea());
  }

  createFormAddForm() {
    this.formAddForm = this.formBuilder.group({
      formAreas: this.formBuilder.array([
      ])
    });
    this.formAreas = this.formAddForm.get('formAreas') as FormArray;
    this.formAreas.push(this.initFormArea());
  }

  initFormArea(): FormGroup {
    return this.formBuilder.group({
      label: ['', Validators.required],
      dataType: ['', Validators.required],
      requirement: ['', Validators.required]
    });
  }

  add(){
    if(this.formAddForm.valid){
      for(let i = 0; i<this.formAreas.length; i++){
        this.formArea = Object.assign({},this.formAddForm.get("formAreas").value[i]);
        this.submitted = true;
        this.formAreaService.createFormArea(this.formArea, this.postType.id)
      .subscribe(data => {
        this.formArea = data['formArea'];
      }, error => console.log(error));
      }
    }
  }
}