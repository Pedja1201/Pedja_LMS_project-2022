import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaMestaComponent } from './tabela-mesta.component';

describe('TabelaMestaComponent', () => {
  let component: TabelaMestaComponent;
  let fixture: ComponentFixture<TabelaMestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaMestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaMestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
