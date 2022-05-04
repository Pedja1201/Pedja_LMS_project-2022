import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaPredmetaComponent } from './tabela-predmeta.component';

describe('TabelaPredmetaComponent', () => {
  let component: TabelaPredmetaComponent;
  let fixture: ComponentFixture<TabelaPredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaPredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaPredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
