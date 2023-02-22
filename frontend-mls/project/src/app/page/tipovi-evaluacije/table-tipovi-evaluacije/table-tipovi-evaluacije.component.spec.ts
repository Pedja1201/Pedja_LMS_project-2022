import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableTipoviEvaluacijeComponent } from './table-tipovi-evaluacije.component';

describe('TableTipoviEvaluacijeComponent', () => {
  let component: TableTipoviEvaluacijeComponent;
  let fixture: ComponentFixture<TableTipoviEvaluacijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableTipoviEvaluacijeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableTipoviEvaluacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
