import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsStudentaNaGodiniComponent } from './details-studenta-na-godini.component';

describe('DetailsStudentaNaGodiniComponent', () => {
  let component: DetailsStudentaNaGodiniComponent;
  let fixture: ComponentFixture<DetailsStudentaNaGodiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsStudentaNaGodiniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsStudentaNaGodiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
