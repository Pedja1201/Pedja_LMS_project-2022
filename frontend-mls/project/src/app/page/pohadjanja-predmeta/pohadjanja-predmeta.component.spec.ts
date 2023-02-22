import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PohadjanjaPredmetaComponent } from './pohadjanja-predmeta.component';

describe('PohadjanjaPredmetaComponent', () => {
  let component: PohadjanjaPredmetaComponent;
  let fixture: ComponentFixture<PohadjanjaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PohadjanjaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PohadjanjaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
