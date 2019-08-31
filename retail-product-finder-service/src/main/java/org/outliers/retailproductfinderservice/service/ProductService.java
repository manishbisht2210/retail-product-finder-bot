/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.service;

import org.outliers.retailproductfinderservice.objects.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(String productId);

    void deleteById(String productId);

    Page<Product> findByTenant(PageRequest request, String tenantId, String searchKeyWord);

    Product findByProductName(String productName);
}

