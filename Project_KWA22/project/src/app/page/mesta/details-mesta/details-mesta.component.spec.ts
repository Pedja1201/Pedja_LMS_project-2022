import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsMestaComponent } from './details-mesta.component';

describe('DetailsMestaComponent', () => {
  let component: DetailsMestaComponent;
  let fixture: ComponentFixture<DetailsMestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsMestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsMestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
