import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductsComponent} from './products.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {TruncateDatePipe} from '../../../pipes/truncate-date-pipe';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {HttpClientModule} from '@angular/common/http';
import {RouterTestingModule} from '@angular/router/testing';

describe('ProductsComponent', () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ProductsComponent, TruncateDatePipe],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [FormsModule, BsDropdownModule.forRoot(), HttpClientModule, RouterTestingModule],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
