import { TestBed } from '@angular/core/testing';

import { RealizacijePredmetaService } from './realizacije-predmeta.service';

describe('RealizacijePredmetaService', () => {
  let service: RealizacijePredmetaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RealizacijePredmetaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
