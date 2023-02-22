import { TestBed } from '@angular/core/testing';

import { IshodiNastaveService } from './ishodi-nastave.service';

describe('IshodiNastaveService', () => {
  let service: IshodiNastaveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IshodiNastaveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
