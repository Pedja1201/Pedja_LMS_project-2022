import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikRegisterComponent } from './nastavnik-register.component';

describe('NastavnikRegisterComponent', () => {
  let component: NastavnikRegisterComponent;
  let fixture: ComponentFixture<NastavnikRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NastavnikRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NastavnikRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
