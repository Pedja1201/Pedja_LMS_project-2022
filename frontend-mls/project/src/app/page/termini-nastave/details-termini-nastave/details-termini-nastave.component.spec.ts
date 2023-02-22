import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsTerminiNastaveComponent } from './details-termini-nastave.component';

describe('DetailsTerminiNastaveComponent', () => {
  let component: DetailsTerminiNastaveComponent;
  let fixture: ComponentFixture<DetailsTerminiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsTerminiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsTerminiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
