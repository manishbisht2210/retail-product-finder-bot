import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TenantService} from 'src/app/services/tenant/tenant.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-tenant-update',
  templateUrl: './tenant-update.component.html',
  styleUrls: ['./tenant-update.component.css']
})
export class TenantUpdateComponent implements OnInit {
  angForm = new FormGroup({
    tenantName: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    type: new FormControl('', Validators.required),
    tenantId: new FormControl('', Validators.required),
    createdAt: new FormControl(''),
    updatedAt: new FormControl(''),
  });

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tenantService: TenantService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const tenantId = params['tenantId'];
      this.tenantService.getTenant(tenantId).subscribe(tenant => {
        this.setValue(tenant);
      });
    });
  }

  setValue(tenant: any) {
    this.angForm.setValue(tenant);
  }

  updateTenant() {
    this.route.params.subscribe(params => {
      const tenant = this.angForm.value;
      this.tenantService.updateTenant(tenant.tenantId, tenant).subscribe(
        result => console.log(result)
      );
      this.router.navigate(['tenants']);
    });
  }

}
