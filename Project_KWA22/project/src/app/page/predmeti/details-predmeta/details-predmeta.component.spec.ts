import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsPredmetaComponent } from './details-predmeta.component';

describe('DetailsPredmetaComponent', () => {
  let component: DetailsPredmetaComponent;
  let fixture: ComponentFixture<DetailsPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
