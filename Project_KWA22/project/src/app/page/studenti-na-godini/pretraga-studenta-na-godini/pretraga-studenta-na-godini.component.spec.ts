import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaStudentaNaGodiniComponent } from './pretraga-studenta-na-godini.component';

describe('PretragaStudentaNaGodiniComponent', () => {
  let component: PretragaStudentaNaGodiniComponent;
  let fixture: ComponentFixture<PretragaStudentaNaGodiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaStudentaNaGodiniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PretragaStudentaNaGodiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
