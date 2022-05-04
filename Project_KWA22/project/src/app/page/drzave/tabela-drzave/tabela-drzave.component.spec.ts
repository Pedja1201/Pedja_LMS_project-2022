import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaDrzaveComponent } from './tabela-drzave.component';

describe('TabelaDrzaveComponent', () => {
  let component: TabelaDrzaveComponent;
  let fixture: ComponentFixture<TabelaDrzaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabelaDrzaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaDrzaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
