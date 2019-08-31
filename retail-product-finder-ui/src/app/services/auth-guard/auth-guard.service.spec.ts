import {TestBed} from '@angular/core/testing';

import {AuthGuardService} from './auth-guard.service';
import {RouterTestingModule} from '@angular/router/testing';
import {TokenService} from '../token/token.service';

describe('AuthGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [TokenService, AuthGuardService]
    });
  });

  it('should be created', () => {
    const service: AuthGuardService = TestBed.get(AuthGuardService);
    expect(service).toBeTruthy();
  });
});
