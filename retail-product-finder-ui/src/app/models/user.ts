import {Tenant} from './tenant';

export class User {
  userName: string;
  password?: string;
  email?: string;
  tenantName: string;
  tenant?: Tenant[];
  fullName?: string;
}
