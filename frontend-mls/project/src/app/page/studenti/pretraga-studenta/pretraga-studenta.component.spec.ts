import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaStudentaComponent } from './pretraga-studenta.component';

describe('PretragaStudentaComponent', () => {
  let component: PretragaStudentaComponent;
  let fixture: ComponentFixture<PretragaStudentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaStudentaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PretragaStudentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
