import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerminiNastaveComponent } from './termini-nastave.component';

describe('TerminiNastaveComponent', () => {
  let component: TerminiNastaveComponent;
  let fixture: ComponentFixture<TerminiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TerminiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TerminiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
