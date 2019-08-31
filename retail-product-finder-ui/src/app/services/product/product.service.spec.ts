import {TestBed} from '@angular/core/testing';

import {ProductService} from './product.service';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('ProductService', () => {
  let httpMock: HttpTestingController;
  let productService: ProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule]
    });
    httpMock = TestBed.get(HttpTestingController);
    productService = TestBed.get(ProductService);
  });

  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpMock.verify();
  });

  it('should be created', () => {
    expect(productService).toBeTruthy();
  });
});
