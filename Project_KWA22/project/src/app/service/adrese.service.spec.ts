import { TestBed } from '@angular/core/testing';

import { AdreseService } from './adrese.service';

describe('AdreseService', () => {
  let service: AdreseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdreseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
