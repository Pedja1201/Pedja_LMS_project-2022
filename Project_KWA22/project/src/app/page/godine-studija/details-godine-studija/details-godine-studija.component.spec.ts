import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsGodineStudijaComponent } from './details-godine-studija.component';

describe('DetailsGodineStudijaComponent', () => {
  let component: DetailsGodineStudijaComponent;
  let fixture: ComponentFixture<DetailsGodineStudijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsGodineStudijaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsGodineStudijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
