import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaTipaNastaveComponent } from './tabela-tipa-nastave.component';

describe('TabelaTipaNastaveComponent', () => {
  let component: TabelaTipaNastaveComponent;
  let fixture: ComponentFixture<TabelaTipaNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaTipaNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaTipaNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
