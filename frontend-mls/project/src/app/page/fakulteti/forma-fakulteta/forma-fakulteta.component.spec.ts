import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaFakultetaComponent } from './forma-fakulteta.component';

describe('FormaFakultetaComponent', () => {
  let component: FormaFakultetaComponent;
  let fixture: ComponentFixture<FormaFakultetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaFakultetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaFakultetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
