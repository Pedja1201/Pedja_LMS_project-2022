import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaNastavnikaComponent } from './tabela-nastavnika.component';

describe('TabelaNastavnikaComponent', () => {
  let component: TabelaNastavnikaComponent;
  let fixture: ComponentFixture<TabelaNastavnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaNastavnikaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaNastavnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
