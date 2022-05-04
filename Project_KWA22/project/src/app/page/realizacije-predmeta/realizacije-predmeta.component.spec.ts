import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RealizacijePredmetaComponent } from './realizacije-predmeta.component';

describe('RealizacijePredmetaComponent', () => {
  let component: RealizacijePredmetaComponent;
  let fixture: ComponentFixture<RealizacijePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RealizacijePredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RealizacijePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
