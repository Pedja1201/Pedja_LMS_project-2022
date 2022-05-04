import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaAdreseComponent } from './forma-adrese.component';

describe('FormaAdreseComponent', () => {
  let component: FormaAdreseComponent;
  let fixture: ComponentFixture<FormaAdreseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaAdreseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaAdreseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
