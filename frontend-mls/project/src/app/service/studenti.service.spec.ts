import { TestBed } from '@angular/core/testing';

import { StudentiService } from './studenti.service';

describe('StudentiService', () => {
  let service: StudentiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
