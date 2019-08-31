import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductDetailComponent} from './product-detail.component';
import {HttpClientModule} from '@angular/common/http';
import {TruncateDatePipe} from '../../../pipes/truncate-date-pipe';
import {RouterTestingModule} from '@angular/router/testing';

describe('ProductDetailComponent', () => {
  let component: ProductDetailComponent;
  let fixture: ComponentFixture<ProductDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ProductDetailComponent, TruncateDatePipe],
      imports: [HttpClientModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
