import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaTipaZvanjaComponent } from './tabela-tipa-zvanja.component';

describe('TabelaTipaZvanjaComponent', () => {
  let component: TabelaTipaZvanjaComponent;
  let fixture: ComponentFixture<TabelaTipaZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaTipaZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaTipaZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
