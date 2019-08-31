/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.config;

import static org.outliers.retailproductfinderservice.objects.model.RoleName.ROLE_ADMIN;
import static org.outliers.retailproductfinderservice.objects.model.RoleName.ROLE_CUSTOMER;
import static org.outliers.retailproductfinderservice.objects.model.RoleName.ROLE_USER;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.outliers.retailproductfinderservice.objects.model.Role;
import org.outliers.retailproductfinderservice.objects.model.RoleName;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.outliers.retailproductfinderservice.objects.model.User;
import org.outliers.retailproductfinderservice.service.RoleService;
import org.outliers.retailproductfinderservice.service.TenantService;
import org.outliers.retailproductfinderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DataInitializer implements CommandLineRunner {

  private RoleService roleService;
  private UserService userService;
  private TenantService tenantService;
  private InfosysTenantConfig infosysTenantConfig;

  @Autowired
  public DataInitializer(RoleService roleService,
      UserService userService,
      TenantService tenantService,
      InfosysTenantConfig infosysTenantConfig) {
    this.roleService = roleService;
    this.userService = userService;
    this.tenantService = tenantService;
    this.infosysTenantConfig = infosysTenantConfig;
  }

  @Override
  @Transactional
  public void run(String... strings) {
    List<Role> roles = roleService.findAll();
    if (roles.isEmpty()) {
      List<RoleName> roleNames = Arrays.asList(ROLE_ADMIN, ROLE_CUSTOMER, ROLE_USER);
      roleNames.forEach(roleName -> {
        Role role = new Role();
        role.setName(roleName);
        roleService.save(role);
      });
    } else {
      log.info("roles are already populated. No need to populate again.");
    }
    List<Tenant> tenants = tenantService.findAll();
    if (tenants.isEmpty()) {
      Tenant tenant = new Tenant();
      tenant.setTenantName(infosysTenantConfig.getTenantname());
      Tenant infosysTenant = tenantService.save(tenant);
      User user = new User();
      user.setUserName(infosysTenantConfig.getAdminusername());
      user.setEmail(infosysTenantConfig.getAdminemail());
      user.setPassword(infosysTenantConfig.getAdminpassword());
      user.setTenants(new HashSet<>(Arrays.asList(infosysTenant)));
      //TODO Should Be Just Admin Role
      List<Role> rolesFromDb = roleService.findAll();
      user.setRoles(new HashSet<>(rolesFromDb));
      userService.save(user);
    }
  }

}
