import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsEvaluacijeZnanjaComponent } from './details-evaluacije-znanja.component';

describe('DetailsEvaluacijeZnanjaComponent', () => {
  let component: DetailsEvaluacijeZnanjaComponent;
  let fixture: ComponentFixture<DetailsEvaluacijeZnanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsEvaluacijeZnanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsEvaluacijeZnanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
