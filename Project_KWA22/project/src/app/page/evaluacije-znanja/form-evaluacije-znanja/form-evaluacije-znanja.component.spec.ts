import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEvaluacijeZnanjaComponent } from './form-evaluacije-znanja.component';

describe('FormEvaluacijeZnanjaComponent', () => {
  let component: FormEvaluacijeZnanjaComponent;
  let fixture: ComponentFixture<FormEvaluacijeZnanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormEvaluacijeZnanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormEvaluacijeZnanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
