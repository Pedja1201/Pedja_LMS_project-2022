import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaNastavnikaNaRealizacijiComponent } from './tabela-nastavnika-na-realizaciji.component';

describe('TabelaNastavnikaNaRealizacijiComponent', () => {
  let component: TabelaNastavnikaNaRealizacijiComponent;
  let fixture: ComponentFixture<TabelaNastavnikaNaRealizacijiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaNastavnikaNaRealizacijiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaNastavnikaNaRealizacijiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
