import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaUniverzitetaComponent } from './tabela-univerziteta.component';

describe('TabelaUniverzitetaComponent', () => {
  let component: TabelaUniverzitetaComponent;
  let fixture: ComponentFixture<TabelaUniverzitetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaUniverzitetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaUniverzitetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
