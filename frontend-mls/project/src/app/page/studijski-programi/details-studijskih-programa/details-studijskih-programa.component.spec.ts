import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsStudijskihProgramaComponent } from './details-studijskih-programa.component';

describe('DetailsStudijskihProgramaComponent', () => {
  let component: DetailsStudijskihProgramaComponent;
  let fixture: ComponentFixture<DetailsStudijskihProgramaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsStudijskihProgramaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsStudijskihProgramaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
