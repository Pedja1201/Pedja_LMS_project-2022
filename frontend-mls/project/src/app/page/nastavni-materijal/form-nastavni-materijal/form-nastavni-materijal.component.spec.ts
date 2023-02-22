import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormNastavniMaterijalComponent } from './form-nastavni-materijal.component';

describe('FormNastavniMaterijalComponent', () => {
  let component: FormNastavniMaterijalComponent;
  let fixture: ComponentFixture<FormNastavniMaterijalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormNastavniMaterijalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormNastavniMaterijalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
