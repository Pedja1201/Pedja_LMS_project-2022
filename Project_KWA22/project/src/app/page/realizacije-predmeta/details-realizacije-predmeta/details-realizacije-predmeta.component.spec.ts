import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsRealizacijePredmetaComponent } from './details-realizacije-predmeta.component';

describe('DetailsRealizacijePredmetaComponent', () => {
  let component: DetailsRealizacijePredmetaComponent;
  let fixture: ComponentFixture<DetailsRealizacijePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsRealizacijePredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsRealizacijePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
