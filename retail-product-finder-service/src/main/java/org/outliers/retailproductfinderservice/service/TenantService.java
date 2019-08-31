/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service;

import java.util.List;
import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface TenantService {

  Tenant save(Tenant tenant);

  Optional<Tenant> findByTenantName(String tenantName);

  Optional<Tenant> findById(String id);

  List<Tenant> findAll();

  Page<Tenant> findAll(Pageable pageable);

  void deleteById(String tenantId);

  Page<Tenant> searchTenants(PageRequest request, String searchKeyWord);

}

