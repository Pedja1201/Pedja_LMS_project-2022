import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsIshodaComponent } from './details-ishoda.component';

describe('DetailsIshodaComponent', () => {
  let component: DetailsIshodaComponent;
  let fixture: ComponentFixture<DetailsIshodaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsIshodaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsIshodaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
