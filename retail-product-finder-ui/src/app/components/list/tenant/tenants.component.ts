import {Component, OnInit, TemplateRef} from '@angular/core';
import {TenantService} from 'src/app/services/tenant/tenant.service';
import {Router} from '@angular/router';
import {TokenService} from 'src/app/services/token/token.service';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {NgxSpinnerService} from 'ngx-spinner';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Tenant} from '../../../models/tenant';

@Component({
  selector: 'app-tenants',
  templateUrl: './tenants.component.html',
  styleUrls: ['./tenants.component.css']
})
export class TenantsComponent implements OnInit {
  pageTitle = 'List of Tenants';
  tenants: Tenant[];
  currentPage = 1;
  itemsPerPage = 5;
  totalItems: number;
  maxPages = 0;
  modalRef: BsModalRef;
  searchKeyWord = undefined;

  constructor(private modalService: BsModalService, private tenantService: TenantService,
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
    this.tenantService.searchTenants(pageRequest, this.searchKeyWord)
    .subscribe((res: HttpResponse<Tenant[]>) => {
      this.paginateTenants(res.body, res.headers);
      setTimeout(() => {
        this.spinner.hide();
      }, 300);
    });
  }

  goToDetails(tenantId: string) {
    this.router.navigateByUrl('/tenant-details/' + tenantId);
  }

  updateTenant(tenantId: string) {
    this.router.navigateByUrl('/tenant-update/' + tenantId);
  }

  ngOnInit() {
    this.loadDetailsByPage();
  }

  loadDetailsByPage() {
    this.spinner.show();
    this.tenantService.getTenants(this.buildPageRequest())
    .subscribe((res: HttpResponse<Tenant[]>) => {
      this.paginateTenants(res.body, res.headers);
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

  private paginateTenants(data: Tenant[], headers: HttpHeaders) {
    this.totalItems = parseInt(headers.get('X-Total-Count'));
    this.maxPages = Math.ceil(this.totalItems / this.itemsPerPage);
    // if user is searching after using pagination. CurrentPage value is greater in this case. So need to adjust accordingly
    if (this.currentPage > this.maxPages) {
      this.currentPage = 1;
    }
    this.tenants = data;
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  confirm(tenantId: string): void {
    this.tenantService.deleteTenant(tenantId).subscribe(
      result => {
        this.loadDetailsByPage();
      }
    );
    this.modalRef.hide();
  }

  decline(): void {
    this.modalRef.hide();
  }

  isAllowed() {
    if (this.tokenService.isCustomer() || this.tokenService.isAdmin()) {
      return true;
    }
    return false;
  }

}
