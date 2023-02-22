import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsIshodiNastaveComponent } from './details-ishodi-nastave.component';

describe('DetailsIshodiNastaveComponent', () => {
  let component: DetailsIshodiNastaveComponent;
  let fixture: ComponentFixture<DetailsIshodiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsIshodiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsIshodiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
