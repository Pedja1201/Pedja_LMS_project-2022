import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IshodiComponent } from './ishodi.component';

describe('IshodiComponent', () => {
  let component: IshodiComponent;
  let fixture: ComponentFixture<IshodiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IshodiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IshodiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
