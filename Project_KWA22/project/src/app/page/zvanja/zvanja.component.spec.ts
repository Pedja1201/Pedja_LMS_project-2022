import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZvanjaComponent } from './zvanja.component';

describe('ZvanjaComponent', () => {
  let component: ZvanjaComponent;
  let fixture: ComponentFixture<ZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
