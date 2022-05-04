import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaTipaZvanjaComponent } from './forma-tipa-zvanja.component';

describe('FormaTipaZvanjaComponent', () => {
  let component: FormaTipaZvanjaComponent;
  let fixture: ComponentFixture<FormaTipaZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaTipaZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaTipaZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
