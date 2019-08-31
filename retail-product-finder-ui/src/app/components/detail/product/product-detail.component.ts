import {Component, OnInit} from '@angular/core';
import {Product} from 'src/app/models/product';
import {ProductService} from 'src/app/services/product/product.service';
import {ActivatedRoute} from '@angular/router';
import {TokenService} from '../../../services/token/token.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product: Product;

  constructor(private tokenService: TokenService, private route: ActivatedRoute,
              private productService: ProductService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const productId = params['productId'];
      this.productService.getProduct(productId).subscribe(product => {
        this.product = product;
      });
    });
  }

  getImage(product: Product) {
    return '../../../../assets/profile.png';
  }
}
