import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaGodineStudijaComponent } from './tabela-godine-studija.component';

describe('TabelaGodineStudijaComponent', () => {
  let component: TabelaGodineStudijaComponent;
  let fixture: ComponentFixture<TabelaGodineStudijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaGodineStudijaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaGodineStudijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
