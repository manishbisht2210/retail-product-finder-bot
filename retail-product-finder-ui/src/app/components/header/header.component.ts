import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/services/auth/auth.service';
import {TokenService} from 'src/app/services/token/token.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username: string;
  title: 'Retail Product Finder Service';

  constructor(private router: Router, private authService: AuthService, private tokenService: TokenService) {
  }

  ngOnInit() {
  }


  isLoggedIn() {
    this.username = this.tokenService.getUserNameFromToken();
    return this.authService.isLoggedIn();
  }

  isAdmin() {
    return this.tokenService.isAdmin();
  }

  isUser() {
    return this.tokenService.isUser();
  }

  isCustomer() {
    return this.tokenService.isCustomer();
  }

  onLogout(): void {
    this.authService.logout();
  }

}
