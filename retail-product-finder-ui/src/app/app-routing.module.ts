import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {TenantsComponent} from './components/list/tenant/tenants.component';
import {TenantDetailComponent} from './components/detail/tenant/tenant-detail.component';
import {TenantAddComponent} from './components/add/tenant/tenant-add.component';
import {TenantUpdateComponent} from './components/update/tenant/tenant-update.component';
import {AuthGuardService} from './services/auth-guard/auth-guard.service';
import {RoleGuardService} from './services/role-guard/role-guard.service';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {ProductsComponent} from './components/list/product/products.component';
import {ProductDetailComponent} from './components/detail/product/product-detail.component';
import {ProductAddComponent} from './components/add/product/product-add.component';
import {ProductUpdateComponent} from './components/update/product/product-update.component';
import {UsersComponent} from './components/list/user/users.component';
import {HomeComponent} from './components/home/home.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent,
  },
  {
    path: 'dashboard', component: DashboardComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'tenants',
    component: TenantsComponent,
    data: {title: 'List of Tenants'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'tenant-details/:tenantId',
    component: TenantDetailComponent,
    data: {title: 'Tenant Details'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'tenant-add',
    component: TenantAddComponent,
    data: {title: 'Add Tenant'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'tenant-update/:tenantId',
    component: TenantUpdateComponent,
    data: {title: 'Update Tenant'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'products',
    component: ProductsComponent,
    data: {title: 'List of Products'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'users',
    component: UsersComponent,
    data: {title: 'List of Users'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'product-details/:productId',
    component: ProductDetailComponent,
    data: {title: 'Product Details'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'product-add',
    component: ProductAddComponent,
    data: {title: 'Add Product'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'app-home',
    component: HomeComponent,
    data: {title: 'Home'},
    canActivate: [AuthGuardService]
  },
  {
    path: 'product-update/:productId',
    component: ProductUpdateComponent,
    data: {title: 'Update Product'},
    canActivate: [AuthGuardService]
  },

  {
    path: '',
    redirectTo: '/app-home',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
