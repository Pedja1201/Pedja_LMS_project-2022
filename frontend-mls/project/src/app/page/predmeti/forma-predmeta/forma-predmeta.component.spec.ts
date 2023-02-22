import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaPredmetaComponent } from './forma-predmeta.component';

describe('FormaPredmetaComponent', () => {
  let component: FormaPredmetaComponent;
  let fixture: ComponentFixture<FormaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
