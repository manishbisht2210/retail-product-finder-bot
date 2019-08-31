import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ProductService} from 'src/app/services/product/product.service';
import {Product} from 'src/app/models/product';
import {Router} from '@angular/router';
import {TenantService} from '../../../services/tenant/tenant.service';
import {Tenant} from '../../../models/tenant';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {
  pageTitle = 'Add an Product';
  angForm = new FormGroup({
    productName: new FormControl('', Validators.required),
    createdAt: new FormControl(''),
    updatedAt: new FormControl(''),
    productId: new FormControl(''),
  });

  constructor(private router: Router,
              private productService: ProductService,
              private fb: FormBuilder, private tenantService: TenantService) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      productName: new FormControl('', Validators.required),
      createdAt: new FormControl(''),
      updatedAt: new FormControl(''),
      productId: new FormControl(''),
    });
  }

  addProduct(formData) {
    console.log(formData);
    const product = new Product();
    product.productName = formData.value.productName;
    this.productService.addProduct(product).subscribe(
      result => {
        this.router.navigate(['products']);
      }
    );
  }

  ngOnInit(): void {
  }

}
