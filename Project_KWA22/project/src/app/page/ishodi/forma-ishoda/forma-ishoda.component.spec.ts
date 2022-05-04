import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaIshodaComponent } from './forma-ishoda.component';

describe('FormaIshodaComponent', () => {
  let component: FormaIshodaComponent;
  let fixture: ComponentFixture<FormaIshodaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaIshodaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaIshodaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
