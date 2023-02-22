import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableIshodiNastaveComponent } from './table-ishodi-nastave.component';

describe('TableIshodiNastaveComponent', () => {
  let component: TableIshodiNastaveComponent;
  let fixture: ComponentFixture<TableIshodiNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableIshodiNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableIshodiNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
