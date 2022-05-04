import { TestBed } from '@angular/core/testing';

import { ZvanjaService } from './zvanja.service';

describe('ZvanjaService', () => {
  let service: ZvanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZvanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
