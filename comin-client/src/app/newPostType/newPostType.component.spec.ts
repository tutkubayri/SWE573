import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPostTypeComponent } from './newPostType.component';

describe('NewPostTypeComponent', () => {
  let component: NewPostTypeComponent;
  let fixture: ComponentFixture<NewPostTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewPostTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPostTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
