import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsPolaganjaComponent } from './details-polaganja.component';

describe('DetailsPolaganjaComponent', () => {
  let component: DetailsPolaganjaComponent;
  let fixture: ComponentFixture<DetailsPolaganjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsPolaganjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsPolaganjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
