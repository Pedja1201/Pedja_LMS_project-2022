import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaPohadjanjaPredmetaComponent } from './forma-pohadjanja-predmeta.component';

describe('FormaPohadjanjaPredmetaComponent', () => {
  let component: FormaPohadjanjaPredmetaComponent;
  let fixture: ComponentFixture<FormaPohadjanjaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaPohadjanjaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaPohadjanjaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
