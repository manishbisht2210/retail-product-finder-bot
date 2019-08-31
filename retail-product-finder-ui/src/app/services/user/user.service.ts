import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';

import {User} from '../../models/user';
import {createRequestOption} from '../../helpers/request-util';
import {environment} from '../../../environments/environment';
import {TokenService} from '../token/token.service';
import {Tenant} from '../../models/tenant';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = environment.API_URL + '/api/v1/users';

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  // for search functionality but can be integrated with other method i.e. getUsers
  searchUsers(req?: any, searchKeyWord?: string) {
    let newUrl = this.apiUrl;
    const options = createRequestOption(req);
    if (searchKeyWord !== '' && searchKeyWord !== undefined) {
      newUrl = newUrl + '?searchKeyWord=' + searchKeyWord + '&';
    } else {
      newUrl = newUrl + '?';
    }
    return this.http.get<any>(newUrl + 'tenantId=' + this.tokenService.getTenantId(), {params: options, observe: 'response'});
  }

  getUsers(req?: any): Observable<HttpResponse<User[]>> {
    const options = createRequestOption(req);
    return this.http.get<any>(this.apiUrl + '?tenantId=' + this.tokenService.getTenantId(), {params: options, observe: 'response'});
  }

  getUser(userId: string): Observable<User> {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.get<User>(url).pipe(
      tap(_ => console.log(`fetched user userId=${userId}`)),
      catchError(this.handleError<User>(`getUser userId=${userId}`))
    );
  }

  addUser(new_user): Observable<User> {
    return this.http.post<User>(this.apiUrl, new_user, httpOptions).pipe(
      tap(() => console.log(`added user userId`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  updateUser(userId, user): Observable<any> {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.put(url, user, httpOptions).pipe(
      tap(_ => console.log(`updated user userId=${userId}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }

  deleteUser(userId): Observable<User> {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.delete<User>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted user userId=${userId}`)),
      catchError(this.handleError<User>('deleteUser'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
