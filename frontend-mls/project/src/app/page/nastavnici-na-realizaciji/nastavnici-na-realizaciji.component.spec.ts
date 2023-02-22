import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavniciNaRealizacijiComponent } from './nastavnici-na-realizaciji.component';

describe('NastavniciNaRealizacijiComponent', () => {
  let component: NastavniciNaRealizacijiComponent;
  let fixture: ComponentFixture<NastavniciNaRealizacijiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NastavniciNaRealizacijiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NastavniciNaRealizacijiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
