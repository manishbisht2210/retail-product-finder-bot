import {TestBed} from '@angular/core/testing';

import {AuthService} from './auth.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RouterTestingModule} from '@angular/router/testing';

describe('AuthService', () => {
  let authService: AuthService;
  let httpClient: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, RouterTestingModule],
      providers: [AuthService, HttpClient]
    });
    authService = TestBed.get(AuthService);
    httpClient = TestBed.get(HttpClient);
  });

  it('should be created', () => {
    expect(authService).toBeTruthy();
  });
});
