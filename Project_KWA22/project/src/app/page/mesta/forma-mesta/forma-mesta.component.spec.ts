import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaMestaComponent } from './forma-mesta.component';

describe('FormaMestaComponent', () => {
  let component: FormaMestaComponent;
  let fixture: ComponentFixture<FormaMestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaMestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaMestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
