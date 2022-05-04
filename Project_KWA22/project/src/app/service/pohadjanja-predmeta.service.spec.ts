import { TestBed } from '@angular/core/testing';

import { PohadjanjaPredmetaService } from './pohadjanja-predmeta.service';

describe('PohadjanjaPredmetaService', () => {
  let service: PohadjanjaPredmetaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PohadjanjaPredmetaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
