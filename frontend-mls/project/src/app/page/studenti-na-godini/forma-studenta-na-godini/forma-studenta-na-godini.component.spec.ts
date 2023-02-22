import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaStudentaNaGodiniComponent } from './forma-studenta-na-godini.component';

describe('FormaStudentaNaGodiniComponent', () => {
  let component: FormaStudentaNaGodiniComponent;
  let fixture: ComponentFixture<FormaStudentaNaGodiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaStudentaNaGodiniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaStudentaNaGodiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
