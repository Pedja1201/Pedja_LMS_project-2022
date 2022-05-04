import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsPohadjanjaPredmetaComponent } from './details-pohadjanja-predmeta.component';

describe('DetailsPohadjanjaPredmetaComponent', () => {
  let component: DetailsPohadjanjaPredmetaComponent;
  let fixture: ComponentFixture<DetailsPohadjanjaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsPohadjanjaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsPohadjanjaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
