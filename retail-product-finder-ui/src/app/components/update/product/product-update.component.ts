import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from 'src/app/services/product/product.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})
export class ProductUpdateComponent implements OnInit {
  angForm = new FormGroup({
    productName: new FormControl('', Validators.required),
    productId: new FormControl('', Validators.required),
    createdAt: new FormControl(''),
    updatedAt: new FormControl(''),
    tenant: new FormControl(''),
  });

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const productId = params['productId'];
      this.productService.getProduct(productId).subscribe(product => {
        this.setValue(product);
      });
    });
  }

  setValue(product: any) {
    this.angForm.setValue(product);
  }

  updateProduct() {
    this.route.params.subscribe(params => {
      const product = this.angForm.value;
      this.productService.updateProduct(product.productId, product).subscribe(
        result => console.log(result)
      );
      this.router.navigate(['products']);
    });
  }

}
