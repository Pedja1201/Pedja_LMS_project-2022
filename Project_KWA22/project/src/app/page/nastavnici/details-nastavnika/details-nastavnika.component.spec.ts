import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsNastavnikaComponent } from './details-nastavnika.component';

describe('DetailsNastavnikaComponent', () => {
  let component: DetailsNastavnikaComponent;
  let fixture: ComponentFixture<DetailsNastavnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsNastavnikaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsNastavnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
