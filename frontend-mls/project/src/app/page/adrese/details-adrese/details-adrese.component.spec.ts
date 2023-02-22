import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsAdreseComponent } from './details-adrese.component';

describe('DetailsAdreseComponent', () => {
  let component: DetailsAdreseComponent;
  let fixture: ComponentFixture<DetailsAdreseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsAdreseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsAdreseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
