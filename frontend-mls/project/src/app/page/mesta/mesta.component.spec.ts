import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MestaComponent } from './mesta.component';

describe('MestaComponent', () => {
  let component: MestaComponent;
  let fixture: ComponentFixture<MestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
