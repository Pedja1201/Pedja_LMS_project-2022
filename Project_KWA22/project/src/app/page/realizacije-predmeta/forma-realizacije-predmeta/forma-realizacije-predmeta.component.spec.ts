import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormaRealizacijePredmetaComponent } from './forma-realizacije-predmeta.component';

describe('FormaRealizacijePredmetaComponent', () => {
  let component: FormaRealizacijePredmetaComponent;
  let fixture: ComponentFixture<FormaRealizacijePredmetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormaRealizacijePredmetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormaRealizacijePredmetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
