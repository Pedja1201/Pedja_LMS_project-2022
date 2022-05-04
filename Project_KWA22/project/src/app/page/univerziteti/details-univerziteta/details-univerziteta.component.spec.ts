import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsUniverzitetaComponent } from './details-univerziteta.component';

describe('DetailsUniverzitetaComponent', () => {
  let component: DetailsUniverzitetaComponent;
  let fixture: ComponentFixture<DetailsUniverzitetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsUniverzitetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsUniverzitetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
