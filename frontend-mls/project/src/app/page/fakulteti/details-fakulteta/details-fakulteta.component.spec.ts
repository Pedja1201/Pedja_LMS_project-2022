import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsFakultetaComponent } from './details-fakulteta.component';

describe('DetailsFakultetaComponent', () => {
  let component: DetailsFakultetaComponent;
  let fixture: ComponentFixture<DetailsFakultetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsFakultetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsFakultetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
