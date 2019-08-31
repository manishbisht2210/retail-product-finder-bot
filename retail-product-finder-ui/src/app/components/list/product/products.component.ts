import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductService} from 'src/app/services/product/product.service';
import {Router} from '@angular/router';
import {TokenService} from 'src/app/services/token/token.service';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {NgxSpinnerService} from 'ngx-spinner';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Product} from '../../../models/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  pageTitle = 'List of Products';
  products: Product[];
  currentPage = 1;
  itemsPerPage = 5;
  totalItems: number;
  maxPages = 0;
  modalRef: BsModalRef;
  searchKeyWord = undefined;

  constructor(private modalService: BsModalService, private productService: ProductService,
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
    this.productService.searchProducts(pageRequest, this.searchKeyWord)
    .subscribe((res: HttpResponse<Product[]>) => {
      this.paginateProducts(res.body, res.headers);
      setTimeout(() => {
        this.spinner.hide();
      }, 300);
    });
  }

  goToDetails(productId: string) {
    this.router.navigateByUrl('/product-details/' + productId);
  }

  updateProduct(productId: string) {
    this.router.navigateByUrl('/product-update/' + productId);
  }

  ngOnInit() {
    this.loadDetailsByPage();
  }

  loadDetailsByPage() {
    this.spinner.show();
    this.productService.getProducts(this.buildPageRequest())
    .subscribe((res: HttpResponse<Product[]>) => {
      this.paginateProducts(res.body, res.headers);
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

  private paginateProducts(data: Product[], headers: HttpHeaders) {
    this.totalItems = parseInt(headers.get('X-Total-Count'));
    this.maxPages = Math.ceil(this.totalItems / this.itemsPerPage);
    // if user is searching after using pagination. CurrentPage value is greater in this case. So need to adjust accordingly
    if (this.currentPage > this.maxPages) {
      this.currentPage = 1;
    }
    this.products = data;
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  confirm(productId: string): void {
    this.productService.deleteProduct(productId).subscribe(
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
