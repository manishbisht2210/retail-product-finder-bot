import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from 'src/app/models/user';
import {AuthService} from 'src/app/services/auth/auth.service';
import {TokenService} from 'src/app/services/token/token.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {NgxSpinnerService} from 'ngx-spinner';
import {CommonService} from '../../services/common/common.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  errMessage: string;
  successMessage: string;

  angForm = new FormGroup({
    userName: new FormControl(),
    password: new FormControl(),
    tenantName: new FormControl()
  });

  tenantNames: string[];

  constructor(private commonService: CommonService, private spinner: NgxSpinnerService, private tokenService: TokenService,
              private fb: FormBuilder, private authService: AuthService, private router: Router
  ) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
      tenantName: ['', Validators.required],
    });
  }

  onLogout(): void {
    this.tokenService.removeToken();
  }

  onLogin(formData): void {
    this.spinner.show();
    this.user.userName = formData.value.userName;
    this.user.password = formData.value.password;
    this.user.tenantName = formData.value.tenantName;
    this.authService.login(this.user).subscribe(authToken => {
      this.tokenService.setToken(authToken);
      this.router.navigateByUrl('/app-home');
      this.errMessage = 'You are succcessfully logged in';
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      if (error.error instanceof ErrorEvent) {
        // A client-side or network error occurred. Handle it accordingly.
        this.errMessage = `An error occurred : ${error.error.message}`;
      } else {
        // The backend returned an unsuccessful response code.
        // The response body may contain clues as to what went wrong,
        this.errMessage = `Backend returned code : ${error.status} | ` + `${error.error}`;
      }
    });
  }

  ngOnInit() {
    this.commonService.getAllCommonData().subscribe(allCommonData => {
      this.tenantNames = allCommonData['tenantNames'];
    });
  }

}
