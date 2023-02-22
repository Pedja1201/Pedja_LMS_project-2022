import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaTipaNastaveComponent } from './forma-tipa-nastave.component';

describe('FormaTipaNastaveComponent', () => {
  let component: FormaTipaNastaveComponent;
  let fixture: ComponentFixture<FormaTipaNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaTipaNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaTipaNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
