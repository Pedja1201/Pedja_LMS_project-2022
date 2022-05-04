import { TestBed } from '@angular/core/testing';

import { NastavniciService } from './nastavnici.service';

describe('NastavniciService', () => {
  let service: NastavniciService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NastavniciService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
