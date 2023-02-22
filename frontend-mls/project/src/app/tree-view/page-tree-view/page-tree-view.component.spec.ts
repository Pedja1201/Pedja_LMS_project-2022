import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageTreeViewComponent } from './page-tree-view.component';

describe('PageTreeViewComponent', () => {
  let component: PageTreeViewComponent;
  let fixture: ComponentFixture<PageTreeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageTreeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageTreeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
