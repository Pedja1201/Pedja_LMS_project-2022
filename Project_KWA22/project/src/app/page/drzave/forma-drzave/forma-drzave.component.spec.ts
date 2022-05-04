import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaDrzaveComponent } from './forma-drzave.component';

describe('FormaDrzaveComponent', () => {
  let component: FormaDrzaveComponent;
  let fixture: ComponentFixture<FormaDrzaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaDrzaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaDrzaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
