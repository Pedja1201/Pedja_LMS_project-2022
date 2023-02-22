import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaZvanjaComponent } from './forma-zvanja.component';

describe('FormaZvanjaComponent', () => {
  let component: FormaZvanjaComponent;
  let fixture: ComponentFixture<FormaZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
