/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service.impl;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.outliers.retailproductfinderservice.repository.TenantRepository;
import org.outliers.retailproductfinderservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TenantServiceImpl implements TenantService {

  private TenantRepository tenantRepository;

  @Autowired
  public TenantServiceImpl(
      TenantRepository tenantRepository) {
    this.tenantRepository = tenantRepository;
  }

  @Override
  public Tenant save(Tenant tenant) {
    return tenantRepository.saveAndFlush(tenant);
  }

  @Override
  public Optional<Tenant> findByTenantName(String tenantName) {
    return tenantRepository.findByTenantName(tenantName);
  }

  @Override
  public Optional<Tenant> findById(String tenantId) {
    return tenantRepository.findById(tenantId);
  }

  @Override
  public List<Tenant> findAll() {
    return tenantRepository.findAll();
  }

  @Override
  public Page<Tenant> findAll(Pageable pageable) {
    return tenantRepository.findAll(pageable);
  }

  @Override
  public void deleteById(String tenantId) {
    tenantRepository.deleteById(tenantId);
  }

  @Override
  public Page<Tenant> searchTenants(PageRequest pageable, String searchKeyWord) {
    if (StringUtils.isEmpty(searchKeyWord)) {
      return tenantRepository.findAll(pageable);
    } else {
      return tenantRepository.searchByTenantName(pageable, searchKeyWord.toLowerCase());
    }
  }

}
