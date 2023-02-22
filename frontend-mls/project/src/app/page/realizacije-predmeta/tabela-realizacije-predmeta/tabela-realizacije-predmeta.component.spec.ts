import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaRealizacijePredmetaComponent } from './tabela-realizacije-predmeta.component';

describe('TabelaRealizacijePredmetaComponent', () => {
  let component: TabelaRealizacijePredmetaComponent;
  let fixture: ComponentFixture<TabelaRealizacijePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaRealizacijePredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaRealizacijePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
