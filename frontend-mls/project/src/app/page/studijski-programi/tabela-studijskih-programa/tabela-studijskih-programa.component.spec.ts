import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaStudijskihProgramaComponent } from './tabela-studijskih-programa.component';

describe('TabelaStudijskihProgramaComponent', () => {
  let component: TabelaStudijskihProgramaComponent;
  let fixture: ComponentFixture<TabelaStudijskihProgramaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaStudijskihProgramaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaStudijskihProgramaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
