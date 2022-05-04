import { TestBed } from '@angular/core/testing';

import { NastavniciNaRealizacijiService } from './nastavnici-na-realizaciji.service';

describe('NastavniciNaRealizacijiService', () => {
  let service: NastavniciNaRealizacijiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NastavniciNaRealizacijiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
