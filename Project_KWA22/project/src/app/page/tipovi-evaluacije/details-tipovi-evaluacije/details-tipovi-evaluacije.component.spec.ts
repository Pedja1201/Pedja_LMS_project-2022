import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsTipoviEvaluacijeComponent } from './details-tipovi-evaluacije.component';

describe('DetailsTipoviEvaluacijeComponent', () => {
  let component: DetailsTipoviEvaluacijeComponent;
  let fixture: ComponentFixture<DetailsTipoviEvaluacijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsTipoviEvaluacijeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsTipoviEvaluacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
