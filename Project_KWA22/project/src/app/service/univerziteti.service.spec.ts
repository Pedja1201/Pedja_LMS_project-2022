import { TestBed } from '@angular/core/testing';

import { UniverzitetiService } from './univerziteti.service';

describe('UniverzitetiService', () => {
  let service: UniverzitetiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UniverzitetiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
