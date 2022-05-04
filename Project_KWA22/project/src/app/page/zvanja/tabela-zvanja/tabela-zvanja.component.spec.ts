import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaZvanjaComponent } from './tabela-zvanja.component';

describe('TabelaZvanjaComponent', () => {
  let component: TabelaZvanjaComponent;
  let fixture: ComponentFixture<TabelaZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
