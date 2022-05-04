import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaNaucneOblastiComponent } from './forma-naucne-oblasti.component';

describe('FormaNaucneOblastiComponent', () => {
  let component: FormaNaucneOblastiComponent;
  let fixture: ComponentFixture<FormaNaucneOblastiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaNaucneOblastiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaNaucneOblastiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
