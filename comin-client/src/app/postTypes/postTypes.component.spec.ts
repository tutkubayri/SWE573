import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostTypesComponent } from './postTypes.component';

describe('PostTypesComponent', () => {
  let component: PostTypesComponent;
  let fixture: ComponentFixture<PostTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
