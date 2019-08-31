import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  let;
  apiUrl = environment.API_URL + '/api/v1/commons';

  constructor(private http: HttpClient) {
  }

  getAllCommonData(): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/getAllCommonData');
  }
}
