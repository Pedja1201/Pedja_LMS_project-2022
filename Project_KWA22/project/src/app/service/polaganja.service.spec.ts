import { TestBed } from '@angular/core/testing';

import { PolaganjaService } from './polaganja.service';

describe('PolaganjaService', () => {
  let service: PolaganjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PolaganjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
