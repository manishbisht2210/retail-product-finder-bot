import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TenantService} from 'src/app/services/tenant/tenant.service';
import {Tenant} from 'src/app/models/tenant';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tenant-add',
  templateUrl: './tenant-add.component.html',
  styleUrls: ['./tenant-add.component.css']
})
export class TenantAddComponent implements OnInit {
  pageTitle = 'Add an Tenant';

  angForm = new FormGroup({
    tenantName: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    type: new FormControl('', Validators.required),
    createdAt: new FormControl(''),
    updatedAt: new FormControl(''),
    tenantId: new FormControl('')
  });

  constructor(private router: Router,
              private tenantService: TenantService,
              private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.angForm = this.fb.group({
      tenantName: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      createdAt: new FormControl(''),
      updatedAt: new FormControl(''),
      tenantId: new FormControl(''),
    });
  }

  addTenant(formData) {
    const tenant = new Tenant();
    tenant.tenantName = formData.value.tenantName;
    tenant.city = formData.value.city;
    tenant.type = formData.value.type;
    this.tenantService.addTenant(tenant).subscribe(
      result => {
        this.router.navigate(['tenants']);
      }
    );
  }

}
