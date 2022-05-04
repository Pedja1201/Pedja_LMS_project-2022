import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaStudijskihProgramaComponent } from './forma-studijskih-programa.component';

describe('FormaStudijskihProgramaComponent', () => {
  let component: FormaStudijskihProgramaComponent;
  let fixture: ComponentFixture<FormaStudijskihProgramaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaStudijskihProgramaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaStudijskihProgramaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
