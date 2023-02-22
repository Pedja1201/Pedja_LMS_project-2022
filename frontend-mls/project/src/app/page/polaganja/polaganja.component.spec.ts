import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PolaganjaComponent } from './polaganja.component';

describe('PolaganjaComponent', () => {
  let component: PolaganjaComponent;
  let fixture: ComponentFixture<PolaganjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PolaganjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PolaganjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
