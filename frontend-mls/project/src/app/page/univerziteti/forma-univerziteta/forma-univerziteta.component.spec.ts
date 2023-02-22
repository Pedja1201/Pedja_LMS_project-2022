import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaUniverzitetaComponent } from './forma-univerziteta.component';

describe('FormaUniverzitetaComponent', () => {
  let component: FormaUniverzitetaComponent;
  let fixture: ComponentFixture<FormaUniverzitetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaUniverzitetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaUniverzitetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
