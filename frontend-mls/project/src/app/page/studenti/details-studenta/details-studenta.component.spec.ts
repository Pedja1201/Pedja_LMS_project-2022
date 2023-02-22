import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsStudentaComponent } from './details-studenta.component';

describe('DetailsStudentaComponent', () => {
  let component: DetailsStudentaComponent;
  let fixture: ComponentFixture<DetailsStudentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsStudentaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsStudentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
