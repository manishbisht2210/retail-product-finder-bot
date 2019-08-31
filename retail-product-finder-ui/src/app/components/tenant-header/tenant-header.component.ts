import {Component, DoCheck, OnInit} from '@angular/core';
import {TokenService} from '../../services/token/token.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-tenant-header',
  templateUrl: './tenant-header.component.html',
  styleUrls: ['./tenant-header.component.css']
})
export class TenantHeaderComponent implements OnInit, DoCheck {
   tenantName: string;

  constructor(private tokenService: TokenService, private authService: AuthService) {
  }

  ngOnInit() {
    this.setTenantName();
  }

  getTenantImage() {
    return this.tokenService.getTenantImage();
  }

  isLoggedIn() {
    this.setTenantName();
    return this.authService.isLoggedIn();
  }

  private setTenantName() {
    this.tenantName = this.tokenService.getTenantName();
  }

  ngDoCheck(): void {
    this.setTenantName();
  }

}
