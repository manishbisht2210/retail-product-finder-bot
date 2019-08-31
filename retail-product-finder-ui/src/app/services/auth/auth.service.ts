import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user';
import {AuthToken} from '../../models/authtoken';
import {TokenService} from '../token/token.service';
import {environment} from '../../../environments/environment';

const resourceUrl = environment.API_URL + '/api/v1/auth';

export const TOKEN_NAME = 'token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  login(user: User): Observable<any> {
    return this.http.post<any>(resourceUrl + '/signin', user);
  }

  signup(user: User): Observable<any> {
    return this.http.post<any>(resourceUrl + '/signup', user);
  }

  confirmToken(token: string): Observable<any> {
    const authToken = new AuthToken();
    authToken.token = token;
    return this.http.post<any>(resourceUrl + '/confirmToken', authToken);
  }

  isLoggedIn(): boolean {
    return this.tokenService.getToken() !== null ? true : false;
  }

  logout() {
    this.tokenService.removeToken();
  }

}
