import { TestBed } from '@angular/core/testing';

import { GodineStudijaService } from './godine-studija.service';

describe('GodineStudijaService', () => {
  let service: GodineStudijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GodineStudijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
