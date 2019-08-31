import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';

import {Tenant} from '../../models/tenant';
import {createRequestOption} from '../../helpers/request-util';
import {environment} from '../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TenantService {

  apiUrl = environment.API_URL + '/api/v1/tenants';

  constructor(private http: HttpClient) {
  }

  // for search functionality but can be integrated with other method i.e. getTenants
  searchTenants(req?: any, searchKeyWord?: string) {
    let newUrl = this.apiUrl;
    const options = createRequestOption(req);
    if (searchKeyWord !== '' && searchKeyWord !== undefined) {
      newUrl = newUrl + '?searchKeyWord=' + searchKeyWord + '&';
    } else {
      newUrl = newUrl + '?';
    }
    return this.http.get<any>(newUrl, {params: options, observe: 'response'});
  }

  getTenants(req?: any): Observable<HttpResponse<Tenant[]>> {
    const options = createRequestOption(req);
    return this.http.get<any>(this.apiUrl, {params: options, observe: 'response'});
  }

  getTenant(tenantId: string): Observable<Tenant> {
    const url = `${this.apiUrl}/${tenantId}`;
    return this.http.get<Tenant>(url).pipe(
      tap(_ => console.log(`fetched tenant tenantId=${tenantId}`)),
      catchError(this.handleError<Tenant>(`getTenant tenantId=${tenantId}`))
    );
  }

  addTenant(new_tenant): Observable<Tenant> {
    return this.http.post<Tenant>(this.apiUrl, new_tenant, httpOptions).pipe(
      tap(() => console.log(`added tenant tenantId`)),
      catchError(this.handleError<Tenant>('addTenant'))
    );
  }

  updateTenant(tenantId, tenant): Observable<any> {
    const url = `${this.apiUrl}/${tenantId}`;
    return this.http.put(url, tenant, httpOptions).pipe(
      tap(_ => console.log(`updated tenant tenantId=${tenantId}`)),
      catchError(this.handleError<any>('updateTenant'))
    );
  }

  deleteTenant(tenantId): Observable<Tenant> {
    const url = `${this.apiUrl}/${tenantId}`;
    return this.http.delete<Tenant>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted tenant tenantId=${tenantId}`)),
      catchError(this.handleError<Tenant>('deleteTenant'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
