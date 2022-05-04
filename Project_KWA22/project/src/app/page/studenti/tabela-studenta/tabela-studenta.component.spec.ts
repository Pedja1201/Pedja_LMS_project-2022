import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaStudentaComponent } from './tabela-studenta.component';

describe('TabelaStudentaComponent', () => {
  let component: TabelaStudentaComponent;
  let fixture: ComponentFixture<TabelaStudentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaStudentaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaStudentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
