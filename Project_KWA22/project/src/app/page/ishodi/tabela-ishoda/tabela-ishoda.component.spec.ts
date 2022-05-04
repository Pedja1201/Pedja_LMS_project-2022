import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaIshodaComponent } from './tabela-ishoda.component';

describe('TabelaIshodaComponent', () => {
  let component: TabelaIshodaComponent;
  let fixture: ComponentFixture<TabelaIshodaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaIshodaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaIshodaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
