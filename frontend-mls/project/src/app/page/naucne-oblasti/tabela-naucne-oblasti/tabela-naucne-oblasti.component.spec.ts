import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaNaucneOblastiComponent } from './tabela-naucne-oblasti.component';

describe('TabelaNaucneOblastiComponent', () => {
  let component: TabelaNaucneOblastiComponent;
  let fixture: ComponentFixture<TabelaNaucneOblastiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaNaucneOblastiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaNaucneOblastiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
