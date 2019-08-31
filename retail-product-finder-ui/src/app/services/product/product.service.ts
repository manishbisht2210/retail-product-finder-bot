import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';

import {Product} from '../../models/product';
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
export class ProductService {

  apiUrl = environment.API_URL + '/api/v1/products';

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  // for search functionality but can be integrated with other method i.e. getProducts
  searchProducts(req?: any, searchKeyWord?: string) {
    let newUrl = this.apiUrl;
    const options = createRequestOption(req);
    if (searchKeyWord !== '' && searchKeyWord !== undefined) {
      newUrl = newUrl + '?searchKeyWord=' + searchKeyWord + '&';
    } else {
      newUrl = newUrl + '?';
    }
    return this.http.get<any>(newUrl + 'tenantId=' + this.tokenService.getTenantId(), {params: options, observe: 'response'});
  }

  getProducts(req?: any): Observable<HttpResponse<Product[]>> {
    const options = createRequestOption(req);
    return this.http.get<any>(this.apiUrl + '?tenantId=' + this.tokenService.getTenantId(), {params: options, observe: 'response'});
  }

  getProduct(productId: string): Observable<Product> {
    const url = `${this.apiUrl}/${productId}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => console.log(`fetched product productId=${productId}`)),
      catchError(this.handleError<Product>(`getProduct productId=${productId}`))
    );
  }

  addProduct(new_product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, this.addTenant(new_product), httpOptions).pipe(
      tap(() => console.log(`added product productId`)),
      catchError(this.handleError<Product>('addProduct'))
    );
  }

  updateProduct(productId, product): Observable<any> {
    const url = `${this.apiUrl}/${productId}`;
    return this.http.put(url, this.addTenant(product), httpOptions).pipe(
      tap(_ => console.log(`updated product productId=${productId}`)),
      catchError(this.handleError<any>('updateProduct'))
    );
  }

  addTenant(new_product: Product) {
    const tenantName = this.tokenService.getTenantName();
    const tenant = new Tenant();
    tenant.tenantName = tenantName;
    new_product.tenant = tenant;
    return new_product;
  }

  deleteProduct(productId): Observable<Product> {
    const url = `${this.apiUrl}/${productId}`;
    return this.http.delete<Product>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted product productId=${productId}`)),
      catchError(this.handleError<Product>('deleteProduct'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
