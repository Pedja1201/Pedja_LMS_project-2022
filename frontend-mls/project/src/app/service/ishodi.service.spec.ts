import { TestBed } from '@angular/core/testing';

import { IshodiService } from './ishodi.service';

describe('IshodiService', () => {
  let service: IshodiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IshodiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
