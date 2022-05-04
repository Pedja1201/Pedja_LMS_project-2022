import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaGodineStudijaComponent } from './forma-godine-studija.component';

describe('FormaGodineStudijaComponent', () => {
  let component: FormaGodineStudijaComponent;
  let fixture: ComponentFixture<FormaGodineStudijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaGodineStudijaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaGodineStudijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
