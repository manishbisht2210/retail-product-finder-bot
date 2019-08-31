/*
 * ******************************************************
 *  * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *  *
 *  * This file is part of Team Outliers.
 *  *
 *  * Team Outliers can not be copied and/or distributed without the express
 *  * permission of Team Outliers
 *  ******************************************************
 */

package org.outliers.retailproductfinderservice.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.outliers.retailproductfinderservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/commons")
public class CommonsResource {

  private TenantService tenantService;

  @Autowired
  public CommonsResource(TenantService tenantService) {
    this.tenantService = tenantService;
  }

  @GetMapping("/getAllCommonData")
  public ResponseEntity<Map<String, Object>> getAllCommonData() {
    log.debug("REST request to get getAllCommonData");
    Map<String, Object> map = new HashMap<>();
    List<Tenant> tenants = tenantService.findAll();
    List<String> tenantNames = new ArrayList<>();
    tenants.forEach(tenant -> {
      tenantNames.add(tenant.getTenantName());
    });
    map.put("tenantNames", tenantNames);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

}
