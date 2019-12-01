import { TestBed } from '@angular/core/testing';

import { FormAreaService } from './form-area.service';

describe('FormAreaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FormAreaService = TestBed.get(FormAreaService);
    expect(service).toBeTruthy();
  });
});
