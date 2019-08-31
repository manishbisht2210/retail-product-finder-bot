import {Component, OnInit, TemplateRef} from '@angular/core';
import {UserService} from 'src/app/services/user/user.service';
import {Router} from '@angular/router';
import {TokenService} from 'src/app/services/token/token.service';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {NgxSpinnerService} from 'ngx-spinner';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {User} from '../../../models/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  pageTitle = 'List of Users';
  users: User[];
  currentPage = 1;
  itemsPerPage = 5;
  totalItems: number;
  maxPages = 0;
  searchKeyWord = undefined;

  constructor(private userService: UserService,
              private spinner: NgxSpinnerService,
              private router: Router, private tokenService: TokenService) {
  }

  pageChanged(event) {
    this.currentPage = event.page || 1;
    this.itemsPerPage = event.itemsPerPage;
    if (this.searchKeyWord) {
      // pagination for search
      this.searchByKeyWord(this.buildPageRequest());
    } else {
      // load with default page details
      this.loadDetailsByPage();
    }
  }

  searchByKeyWord(pageRequest?: any) {
    // if user directly clicks search button, ideally he should be shown first page
    if (!pageRequest) {
      pageRequest = {
        page: 0,
        size: this.itemsPerPage
      };
      this.currentPage = 1;
    }
    this.spinner.show();
    this.userService.searchUsers(pageRequest, this.searchKeyWord)
    .subscribe((res: HttpResponse<User[]>) => {
      this.paginateUsers(res.body, res.headers);
      setTimeout(() => {
        this.spinner.hide();
      }, 300);
    });
  }

  goToDetails(userId: string) {
    this.router.navigateByUrl('/user-details/' + userId);
  }

  ngOnInit() {
    this.loadDetailsByPage();
  }

  loadDetailsByPage() {
    this.spinner.show();
    this.userService.getUsers(this.buildPageRequest())
    .subscribe((res: HttpResponse<User[]>) => {
      this.paginateUsers(res.body, res.headers);
      setTimeout(() => {
        this.spinner.hide();
      }, 300);
    });
  }

  buildPageRequest(): any {
    const page: any = {
      page: this.currentPage - 1,
      size: this.itemsPerPage,
    };
    return page;
  }

  private paginateUsers(data: User[], headers: HttpHeaders) {
    this.totalItems = parseInt(headers.get('X-Total-Count'));
    this.maxPages = Math.ceil(this.totalItems / this.itemsPerPage);
    // if user is searching after using pagination. CurrentPage value is greater in this case. So need to adjust accordingly
    if (this.currentPage > this.maxPages) {
      this.currentPage = 1;
    }
    this.users = data;
  }

  isAllowed() {
    if (this.tokenService.isCustomer() || this.tokenService.isAdmin()) {
      return true;
    }
    return false;
  }

}
