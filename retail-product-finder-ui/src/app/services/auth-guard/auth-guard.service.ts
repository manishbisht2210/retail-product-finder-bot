import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenService} from '../token/token.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private tokenService: TokenService) {
  }

  canActivate(): boolean {
    if (this.tokenService.isTokenExpired()) {
      this.router.navigateByUrl('/login');
      return false;
    }
    return true;
  }

}
