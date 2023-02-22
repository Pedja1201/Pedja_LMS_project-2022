import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaStudentaComponent } from './forma-studenta.component';

describe('FormaStudentaComponent', () => {
  let component: FormaStudentaComponent;
  let fixture: ComponentFixture<FormaStudentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaStudentaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaStudentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
