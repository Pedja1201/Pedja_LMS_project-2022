import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTipoviEvaluacijeComponent } from './form-tipovi-evaluacije.component';

describe('FormTipoviEvaluacijeComponent', () => {
  let component: FormTipoviEvaluacijeComponent;
  let fixture: ComponentFixture<FormTipoviEvaluacijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTipoviEvaluacijeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormTipoviEvaluacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
