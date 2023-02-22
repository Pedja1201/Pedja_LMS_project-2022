import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaNastavnikaNaRealizacijiComponent } from './forma-nastavnika-na-realizaciji.component';

describe('FormaNastavnikaNaRealizacijiComponent', () => {
  let component: FormaNastavnikaNaRealizacijiComponent;
  let fixture: ComponentFixture<FormaNastavnikaNaRealizacijiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaNastavnikaNaRealizacijiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaNastavnikaNaRealizacijiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
