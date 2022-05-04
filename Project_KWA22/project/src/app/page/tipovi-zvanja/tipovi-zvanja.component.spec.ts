import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoviZvanjaComponent } from './tipovi-zvanja.component';

describe('TipoviZvanjaComponent', () => {
  let component: TipoviZvanjaComponent;
  let fixture: ComponentFixture<TipoviZvanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TipoviZvanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoviZvanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
