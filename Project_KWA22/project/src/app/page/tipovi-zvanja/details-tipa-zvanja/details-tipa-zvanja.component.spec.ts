import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsTipaZvanjaComponent } from './details-tipa-zvanja.component';

describe('DetailsTipaZvanjaComponent', () => {
  let component: DetailsTipaZvanjaComponent;
  let fixture: ComponentFixture<DetailsTipaZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsTipaZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsTipaZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
