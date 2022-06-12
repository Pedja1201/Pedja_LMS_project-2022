import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableEvaluacijeZnanjaComponent } from './table-evaluacije-znanja.component';

describe('TableEvaluacijeZnanjaComponent', () => {
  let component: TableEvaluacijeZnanjaComponent;
  let fixture: ComponentFixture<TableEvaluacijeZnanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableEvaluacijeZnanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableEvaluacijeZnanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
