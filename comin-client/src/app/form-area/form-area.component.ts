import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { FormArea } from '../formArea';
import { FormAreaService } from '../services/form-area.service';
import { PostType } from '../postType';
import { ActivatedRoute, Router } from '@angular/router';
import { PostTypeService } from '../services/postType.service';
import { Enum } from '../enum';

@Component({
  selector: 'app-form-area',
  templateUrl: './form-area.component.html',
  styleUrls: ['./form-area.component.css']
})
export class FormAreaComponent implements OnInit {

  @Input() id: number;
  submitted = false;
  enumSubmitted = false;
  formAddForm: FormGroup;
  enumForm: FormGroup;
  formAreas: FormArray;
  formArea: FormArea;
  postType: PostType;
  redirect = false;
  enum = false;
  nameOfControls: Array<string>;
  enumOfFormArea: Enum;
  idList: Array<number>;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private formAreaService: FormAreaService,
    private postTypeService: PostTypeService, private router: Router) { }

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

  add() {
    this.enumForm = this.formBuilder.group({
    });
    this.idList = new Array<number>();
    this.nameOfControls = new Array<string>();
    for (let i = 0; i < this.formAreas.length; i++) {
      this.formArea = Object.assign({}, this.formAddForm.get("formAreas").value[i]);
      this.submitted = true;
      this.formAreaService.createFormArea(this.formArea, this.postType.id)
        .subscribe(data => {
          this.formArea = data['formArea'];
            if(data['dataType'] == "enum"){
              this.enumForm.addControl("enumValues" + "," + data['id'], new FormControl());
              this.enum = true;
              this.idList.push(data['id']);
              this.nameOfControls.push('enumValues' + "," + data['id']);
              this.check(this.enum);
            }
        }, error => console.log(error));
    }console.log(this.enum)
  }

  check(e: boolean){
    if(e == false){
      this.redirect = true;
      this.wait();
    }
  }

  saveEnumValues(list: Array<number>, nameList: Array<string>){
    let l = list;
    let n = nameList;
    for(let j = 0; j<l.length; j++){
      console.log(l.length);
      let trial = {id: null, value: this.enumForm.controls[n[j]].value, formAreaId: null};
      this.enumOfFormArea = Object.assign({}, trial);
      this.enumSubmitted = true;
      this.formAreaService.createEnum(this.enumOfFormArea, l[j])
        .subscribe(data => {
          this.enumOfFormArea = data['enumOfFormArea'];
          this.redirect = true;
          this.wait()
        }, error => console.log(error));
    }
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  async wait() {
    await this.delay(3000);
    this.redirect = false;
    this.router.navigateByUrl('/communities');
  }
}