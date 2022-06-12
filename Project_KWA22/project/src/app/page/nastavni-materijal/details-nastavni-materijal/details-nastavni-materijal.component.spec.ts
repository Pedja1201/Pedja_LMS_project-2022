import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsNastavniMaterijalComponent } from './details-nastavni-materijal.component';

describe('DetailsNastavniMaterijalComponent', () => {
  let component: DetailsNastavniMaterijalComponent;
  let fixture: ComponentFixture<DetailsNastavniMaterijalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsNastavniMaterijalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsNastavniMaterijalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
