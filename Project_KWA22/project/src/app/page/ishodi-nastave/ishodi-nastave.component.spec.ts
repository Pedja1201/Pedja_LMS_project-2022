import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IshodiNastaveComponent } from './ishodi-nastave.component';

describe('IshodiNastaveComponent', () => {
  let component: IshodiNastaveComponent;
  let fixture: ComponentFixture<IshodiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IshodiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IshodiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
