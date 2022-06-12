import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablePolaganjaComponent } from './table-polaganja.component';

describe('TablePolaganjaComponent', () => {
  let component: TablePolaganjaComponent;
  let fixture: ComponentFixture<TablePolaganjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablePolaganjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TablePolaganjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
