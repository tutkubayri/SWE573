import { TestBed } from '@angular/core/testing';

import { NewPostTypeService } from './newPostType.service';

describe('NewPostTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewPostTypeService = TestBed.get(NewPostTypeService);
    expect(service).toBeTruthy();
  });
});
