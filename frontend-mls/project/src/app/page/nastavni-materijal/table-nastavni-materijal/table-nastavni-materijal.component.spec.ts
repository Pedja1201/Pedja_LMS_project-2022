import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableNastavniMaterijalComponent } from './table-nastavni-materijal.component';

describe('TableNastavniMaterijalComponent', () => {
  let component: TableNastavniMaterijalComponent;
  let fixture: ComponentFixture<TableNastavniMaterijalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableNastavniMaterijalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableNastavniMaterijalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
