import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaFakultetaComponent } from './tabela-fakulteta.component';

describe('TabelaFakultetaComponent', () => {
  let component: TabelaFakultetaComponent;
  let fixture: ComponentFixture<TabelaFakultetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaFakultetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaFakultetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
