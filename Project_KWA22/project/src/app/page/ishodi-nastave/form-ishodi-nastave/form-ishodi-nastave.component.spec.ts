import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormIshodiNastaveComponent } from './form-ishodi-nastave.component';

describe('FormIshodiNastaveComponent', () => {
  let component: FormIshodiNastaveComponent;
  let fixture: ComponentFixture<FormIshodiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormIshodiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormIshodiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
