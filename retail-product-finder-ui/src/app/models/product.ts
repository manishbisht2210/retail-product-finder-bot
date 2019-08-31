import {Tenant} from './tenant';

export class Product {
  productId: string;
  productName: string;
  createdAt?: string;
  updatedAt?: string;
  tenant?: Tenant;
}
