import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaucneOblastiComponent } from './naucne-oblasti.component';

describe('NaucneOblastiComponent', () => {
  let component: NaucneOblastiComponent;
  let fixture: ComponentFixture<NaucneOblastiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaucneOblastiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NaucneOblastiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
