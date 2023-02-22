import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaPohadjanjaPredmetaComponent } from './tabela-pohadjanja-predmeta.component';

describe('TabelaPohadjanjaPredmetaComponent', () => {
  let component: TabelaPohadjanjaPredmetaComponent;
  let fixture: ComponentFixture<TabelaPohadjanjaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaPohadjanjaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaPohadjanjaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
