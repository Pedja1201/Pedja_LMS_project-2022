import { TestBed } from '@angular/core/testing';

import { FakultetiService } from './fakulteti.service';

describe('FakultetiService', () => {
  let service: FakultetiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FakultetiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
