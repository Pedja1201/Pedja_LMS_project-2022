import { TestBed } from '@angular/core/testing';

import { StudentiNaGodiniService } from './studenti-na-godini.service';

describe('StudentiNaGodiniService', () => {
  let service: StudentiNaGodiniService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentiNaGodiniService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
