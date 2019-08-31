import {TestBed} from '@angular/core/testing';

import {TenantService} from './tenant.service';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('TenantService', () => {
  let httpMock: HttpTestingController;
  let tenantService: TenantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule]
    });
    httpMock = TestBed.get(HttpTestingController);
    tenantService = TestBed.get(TenantService);
  });

  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpMock.verify();
  });

  it('should be created', () => {
    expect(tenantService).toBeTruthy();
  });
});
