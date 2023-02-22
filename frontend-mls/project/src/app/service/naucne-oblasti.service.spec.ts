import { TestBed } from '@angular/core/testing';

import { NaucneOblastiService } from './naucne-oblasti.service';

describe('NaucneOblastiService', () => {
  let service: NaucneOblastiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NaucneOblastiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
