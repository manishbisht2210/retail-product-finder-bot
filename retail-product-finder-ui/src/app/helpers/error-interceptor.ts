import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {TokenService} from '../services/token/token.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private tokenService: TokenService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.tokenService.removeToken();
        // location.reload(true);
      }
      const error = err.error || err.statusText;
      return throwError(error);
    }));
  }
}
