import { TestBed } from '@angular/core/testing';

import { TipoviNastaveService } from './tipovi-nastave.service';

describe('TipoviNastaveService', () => {
  let service: TipoviNastaveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoviNastaveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
