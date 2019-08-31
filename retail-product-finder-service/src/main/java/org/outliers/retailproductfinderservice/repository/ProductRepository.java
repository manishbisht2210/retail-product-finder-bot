
/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.repository;

import org.outliers.retailproductfinderservice.objects.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  @Query(value = "select distinct product from Product product where product.tenant.tenantId=:tenantId and lower(product.productName) like %:productName%",
      countQuery = "select count(distinct product) from Product product  where product.tenant.tenantId=:tenantId and lower(product.productName) like %:productName%")
  Page<Product> findByTenantIdAndProductName(Pageable pageable, @Param("tenantId") String tenantId,
      @Param("productName") String productName);

  @Query(value = "select distinct product from Product product where product.tenant.tenantId=:tenantId",
      countQuery = "select count(distinct product) from Product product  where product.tenant.tenantId=:tenantId")
  Page<Product> findByTenant(Pageable pageable, @Param("tenantId") String tenantId);

    Product findByProductName(String productName);
}