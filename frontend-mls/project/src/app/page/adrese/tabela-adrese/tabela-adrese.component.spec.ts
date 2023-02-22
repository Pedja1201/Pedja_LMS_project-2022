import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaAdreseComponent } from './tabela-adrese.component';

describe('TabelaAdreseComponent', () => {
  let component: TabelaAdreseComponent;
  let fixture: ComponentFixture<TabelaAdreseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaAdreseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaAdreseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
