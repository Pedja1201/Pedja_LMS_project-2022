import { TestBed } from '@angular/core/testing';

import { TipoviEvaluacijeService } from './tipovi-evaluacije.service';

describe('TipoviEvaluacijeService', () => {
  let service: TipoviEvaluacijeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoviEvaluacijeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
