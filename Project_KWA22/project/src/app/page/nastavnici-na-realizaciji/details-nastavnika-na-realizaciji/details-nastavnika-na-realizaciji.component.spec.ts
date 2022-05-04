import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsNastavnikaNaRealizacijiComponent } from './details-nastavnika-na-realizaciji.component';

describe('DetailsNastavnikaNaRealizacijiComponent', () => {
  let component: DetailsNastavnikaNaRealizacijiComponent;
  let fixture: ComponentFixture<DetailsNastavnikaNaRealizacijiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsNastavnikaNaRealizacijiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsNastavnikaNaRealizacijiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
