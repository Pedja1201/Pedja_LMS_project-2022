import { TestBed } from '@angular/core/testing';

import { TerminiNastaveService } from './termini-nastave.service';

describe('TerminiNastaveService', () => {
  let service: TerminiNastaveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TerminiNastaveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
