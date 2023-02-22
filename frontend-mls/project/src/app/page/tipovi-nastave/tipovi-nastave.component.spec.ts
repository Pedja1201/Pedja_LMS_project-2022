import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoviNastaveComponent } from './tipovi-nastave.component';

describe('TipoviNastaveComponent', () => {
  let component: TipoviNastaveComponent;
  let fixture: ComponentFixture<TipoviNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TipoviNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoviNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
