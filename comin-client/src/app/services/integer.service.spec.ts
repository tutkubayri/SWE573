import { TestBed } from '@angular/core/testing';

import { IntegerService } from './integer.service';

describe('IntegerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IntegerService = TestBed.get(IntegerService);
    expect(service).toBeTruthy();
  });
});
