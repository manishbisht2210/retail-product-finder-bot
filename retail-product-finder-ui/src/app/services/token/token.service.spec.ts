import {TestBed} from '@angular/core/testing';

import {TokenService} from './token.service';
import {Router} from '@angular/router';

class MockRouter {
  navigate = jasmine.createSpy('navigate');
}

// https://github.com/karma-runner/karma-jasmine/issues/221
describe('TokenService', () => {
  let mockRouter = new MockRouter();
  let tokenService: TokenService;
  // tslint:disable-next-line:max-line-length
  const tokenResponse = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWhlbmRyYWJhZ3VsIiwic2NvcGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0NMRVJLIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX1NFTklPUiJ9XSwidGVuYW50TmFtZSI6IlNHR1MiLCJ0ZW5hbnRJZCI6IjI5N2ViNzcyNjZmNDIzY2QwMTY2ZjQyNDRhMjgwMDI4IiwiaXNzIjoiTWJNYW5hZ2UiLCJpYXQiOjE1NDE2Nzk1ODIsImV4cCI6MTU0NzcyNzU4Mn0.7wdmOze0PPEpXJOhDpEBmx5es23VTFbVpQWsZuOEPplirTjvq2il7XXgN2UBf35SMThLxGwaXBNsflAyLUbMRg';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TokenService,
        {provide: Router, useValue: mockRouter}
      ]
    });
    mockRouter = TestBed.get(Router);
    tokenService = TestBed.get(TokenService);
  });

  it('should be created', () => {
    expect(tokenService).toBeTruthy();
  });

  it('should return token', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.getToken();
    expect(response).toEqual(tokenResponse);
  });

  it('should return tenantName', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.getTenantName();
    expect(response).toEqual('SGGS');
  });

  it('should return isTokenExpired', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.isTokenExpired();
    expect(response).toEqual(false);
  });

  it('should return tenantId', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.getTenantId();
    expect(response).toEqual('297eb77266f423cd0166f4244a280028');
  });

  it('should return userNameFromToken', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.getUserNameFromToken();
    expect(response).toEqual('mahendrabagul');
  });

  it('should return isAdmin', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.isAdmin();
    expect(response).toEqual(true);
  });

  it('should return isUser', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.isUser();
    expect(response).toEqual(true);
  });

  it('should return isCustomer', () => {
    let response;
    spyOn(tokenService, 'getToken').and.returnValue(tokenResponse);
    response = tokenService.isCustomer();
    expect(response).toEqual(true);
  });

});
