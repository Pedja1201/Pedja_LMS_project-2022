import { TestBed } from '@angular/core/testing';

import { NastavniMaterijaliService } from './nastavni-materijali.service';

describe('NastavniMaterijaliService', () => {
  let service: NastavniMaterijaliService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NastavniMaterijaliService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
