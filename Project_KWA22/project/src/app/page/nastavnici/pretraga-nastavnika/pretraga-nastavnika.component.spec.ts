import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaNastavnikaComponent } from './pretraga-nastavnika.component';

describe('PretragaNastavnikaComponent', () => {
  let component: PretragaNastavnikaComponent;
  let fixture: ComponentFixture<PretragaNastavnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaNastavnikaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PretragaNastavnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
