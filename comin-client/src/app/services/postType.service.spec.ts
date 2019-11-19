import { TestBed } from '@angular/core/testing';

import { PostTypeService } from './postType.service';

describe('PostService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PostTypeService = TestBed.get(PostTypeService);
    expect(service).toBeTruthy();
  });
});
