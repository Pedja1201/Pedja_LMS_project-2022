import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPolaganjaComponent } from './form-polaganja.component';

describe('FormPolaganjaComponent', () => {
  let component: FormPolaganjaComponent;
  let fixture: ComponentFixture<FormPolaganjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPolaganjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPolaganjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
