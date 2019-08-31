
/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.repository;

import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {

  Optional<Tenant> findByTenantName(String tenantName);

  Boolean existsByTenantName(String tenantName);

  @Query(value = "select distinct tenant from Tenant tenant where lower(tenant.tenantName) like %:tenantName%",
      countQuery = "select count(distinct tenant) from Tenant tenant  where lower(tenant.tenantName) like %:tenantName%")
  Page<Tenant> searchByTenantName(Pageable pageable, @Param("tenantName") String tenantName);

}