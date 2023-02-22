import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableTerminiNastaveComponent } from './table-termini-nastave.component';

describe('TableTerminiNastaveComponent', () => {
  let component: TableTerminiNastaveComponent;
  let fixture: ComponentFixture<TableTerminiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableTerminiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableTerminiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
