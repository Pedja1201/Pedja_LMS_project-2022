import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsTipaNastaveComponent } from './details-tipa-nastave.component';

describe('DetailsTipaNastaveComponent', () => {
  let component: DetailsTipaNastaveComponent;
  let fixture: ComponentFixture<DetailsTipaNastaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsTipaNastaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsTipaNastaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
