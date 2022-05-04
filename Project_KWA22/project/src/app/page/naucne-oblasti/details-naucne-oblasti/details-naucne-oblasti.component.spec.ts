import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsNaucneOblastiComponent } from './details-naucne-oblasti.component';

describe('DetailsNaucneOblastiComponent', () => {
  let component: DetailsNaucneOblastiComponent;
  let fixture: ComponentFixture<DetailsNaucneOblastiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsNaucneOblastiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsNaucneOblastiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
