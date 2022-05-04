import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniverzitetiComponent } from './univerziteti.component';

describe('UniverzitetiComponent', () => {
  let component: UniverzitetiComponent;
  let fixture: ComponentFixture<UniverzitetiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniverzitetiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniverzitetiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
