import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaStudentaNaGodiniComponent } from './tabela-studenta-na-godini.component';

describe('TabelaStudentaNaGodiniComponent', () => {
  let component: TabelaStudentaNaGodiniComponent;
  let fixture: ComponentFixture<TabelaStudentaNaGodiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaStudentaNaGodiniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaStudentaNaGodiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
