import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsZvanjaComponent } from './details-zvanja.component';

describe('DetailsZvanjaComponent', () => {
  let component: DetailsZvanjaComponent;
  let fixture: ComponentFixture<DetailsZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
