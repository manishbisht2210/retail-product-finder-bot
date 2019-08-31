import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {TokenService} from '../token/token.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(public tokenService: TokenService, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config on the data property
    const expectedRole = route.data.expectedRole;
    if (this.tokenService.isTokenExpired()
      || !this.tokenService.hasRole(expectedRole)) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
