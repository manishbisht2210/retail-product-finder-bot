import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {TenantsComponent} from './components/list/tenant/tenants.component';
import {TenantDetailComponent} from './components/detail/tenant/tenant-detail.component';
import {TenantAddComponent} from './components/add/tenant/tenant-add.component';
import {TenantUpdateComponent} from './components/update/tenant/tenant-update.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthService} from './services/auth/auth.service';
import {AuthGuardService} from './services/auth-guard/auth-guard.service';
import {TenantService} from './services/tenant/tenant.service';
import {JwtInterceptor} from './helpers/jwt-interceptor';
import {ErrorInterceptor} from './helpers/error-interceptor';
import {TruncateDatePipe} from './pipes/truncate-date-pipe';
import {CommonService} from './services/common/common.service';
import {StylishPaginationComponent} from './components/stylish-pagination/stylish-pagination.component';
import {ChartsModule} from 'ng2-charts';
import {AlertModule} from 'ngx-bootstrap';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {NgxSpinnerModule} from 'ngx-spinner';
import {ModalModule} from 'ngx-bootstrap/modal';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {TenantHeaderComponent} from './components/tenant-header/tenant-header.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {ProductAddComponent} from './components/add/product/product-add.component';
import {ProductUpdateComponent} from './components/update/product/product-update.component';
import {ProductsComponent} from './components/list/product/products.component';
import {ProductDetailComponent} from './components/detail/product/product-detail.component';
import {ProductService} from './services/product/product.service';
import {UsersComponent} from './components/list/user/users.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    TruncateDatePipe,
    AppComponent,
    UsersComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    DashboardComponent,
    TenantsComponent,
    TenantDetailComponent,
    TenantAddComponent,
    TenantUpdateComponent,
    ProductsComponent,
    ProductDetailComponent,
    ProductAddComponent,
    ProductUpdateComponent,
    StylishPaginationComponent,
    TenantHeaderComponent,
    PageNotFoundComponent,
    HomeComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    ChartsModule,
    ModalModule.forRoot(),
    TooltipModule.forRoot(),
    AlertModule.forRoot(),
    BsDropdownModule.forRoot(),
    NgxSpinnerModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    AuthService, AuthGuardService, TenantService, CommonService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
