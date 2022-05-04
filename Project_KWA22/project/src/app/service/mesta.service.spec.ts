import { TestBed } from '@angular/core/testing';

import { MestaService } from './mesta.service';

describe('MestaService', () => {
  let service: MestaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MestaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
