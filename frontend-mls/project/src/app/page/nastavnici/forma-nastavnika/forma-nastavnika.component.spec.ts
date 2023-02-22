import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaNastavnikaComponent } from './forma-nastavnika.component';

describe('FormaNastavnikaComponent', () => {
  let component: FormaNastavnikaComponent;
  let fixture: ComponentFixture<FormaNastavnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaNastavnikaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaNastavnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
