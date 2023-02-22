import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsDrzavaComponent } from './details-drzava.component';

describe('DetailsDrzavaComponent', () => {
  let component: DetailsDrzavaComponent;
  let fixture: ComponentFixture<DetailsDrzavaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsDrzavaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsDrzavaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
