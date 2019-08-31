import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StylishPaginationComponent} from './stylish-pagination.component';

describe('StylishPaginationComponent', () => {
  let component: StylishPaginationComponent;
  let fixture: ComponentFixture<StylishPaginationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StylishPaginationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StylishPaginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
