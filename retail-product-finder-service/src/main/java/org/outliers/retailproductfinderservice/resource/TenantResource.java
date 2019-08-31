/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.outliers.retailproductfinderservice.exception.ResourceNotFoundException;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.outliers.retailproductfinderservice.service.TenantService;
import org.outliers.retailproductfinderservice.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tenants")
@Slf4j
public class TenantResource {

  public static final String URI = "/tenants";
  private TenantService tenantService;

  @Autowired
  public TenantResource(TenantService tenantService) {
    this.tenantService = tenantService;
  }

  @GetMapping("/{tenantId}")
  public Tenant findTenantById(@PathVariable String tenantId) throws ResourceNotFoundException {
    Optional<Tenant> tenantOptional = tenantService.findById(tenantId);
    if (!tenantOptional.isPresent()) {
      throw new ResourceNotFoundException("tenantId-" + tenantId);
    }
    return tenantOptional.get();
  }

  @GetMapping
  public ResponseEntity<List<Tenant>> searchTenants(Pageable pageable,
      @RequestParam(required = false) String searchKeyWord) {
    log.debug(
        "REST request to get a page of Tenants : {} by optional searchString : {}",
        pageable, searchKeyWord);
    PageRequest request = PaginationUtil.generatePageRequest(pageable);
    Page<Tenant> page = tenantService.searchTenants(request, searchKeyWord);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format(URI));
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  @DeleteMapping("/{tenantId}")
  public void deleteTenant(@PathVariable String tenantId) {
    tenantService.deleteById(tenantId);
  }

  @PutMapping("/{tenantId}")
  public ResponseEntity<Object> updateTenant(@RequestBody Tenant tenant,
      @PathVariable String tenantId) {
    Optional<Tenant> tenantOptional = tenantService.findById(tenantId);
    if (!tenantOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    tenant.setTenantId(tenantId);
    tenantService.save(tenant);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createTenant(@RequestBody Tenant tenant) {
    Tenant savedTenant = tenantService.save(tenant);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{tenantId}")
        .buildAndExpand(savedTenant.getTenantId()).toUri();
    return ResponseEntity.created(location).build();
  }
}
