import { TestBed } from '@angular/core/testing';

import { EvaluacijeZnanjaService } from './evaluacije-znanja.service';

describe('EvaluacijeZnanjaService', () => {
  let service: EvaluacijeZnanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvaluacijeZnanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
