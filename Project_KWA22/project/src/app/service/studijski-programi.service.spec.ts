import { TestBed } from '@angular/core/testing';

import { StudijskiProgramiService } from './studijski-programi.service';

describe('StudijskiProgramiService', () => {
  let service: StudijskiProgramiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudijskiProgramiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
