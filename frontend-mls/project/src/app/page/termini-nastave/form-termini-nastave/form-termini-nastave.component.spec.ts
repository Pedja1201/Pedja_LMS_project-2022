import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTerminiNastaveComponent } from './form-termini-nastave.component';

describe('FormTerminiNastaveComponent', () => {
  let component: FormTerminiNastaveComponent;
  let fixture: ComponentFixture<FormTerminiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTerminiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormTerminiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
