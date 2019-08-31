import {Component, OnInit} from '@angular/core';
import {Tenant} from 'src/app/models/tenant';
import {TenantService} from 'src/app/services/tenant/tenant.service';
import {ActivatedRoute} from '@angular/router';
import {TokenService} from '../../../services/token/token.service';

@Component({
  selector: 'app-tenant-detail',
  templateUrl: './tenant-detail.component.html',
  styleUrls: ['./tenant-detail.component.css']
})
export class TenantDetailComponent implements OnInit {
  tenant: Tenant;

  constructor(private tokenService: TokenService, private route: ActivatedRoute,
              private tenantService: TenantService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const tenantId = params['tenantId'];
      this.tenantService.getTenant(tenantId).subscribe(tenant => {
        this.tenant = tenant;
      });
    });
  }

  getImage(tenant: Tenant) {
    return '../../../../assets/profile.png';
  }
}
