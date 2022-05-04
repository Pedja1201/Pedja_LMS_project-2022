import { TestBed } from '@angular/core/testing';

import { TipoviZvanjaService } from './tipovi-zvanja.service';

describe('TipoviZvanjaService', () => {
  let service: TipoviZvanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoviZvanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
