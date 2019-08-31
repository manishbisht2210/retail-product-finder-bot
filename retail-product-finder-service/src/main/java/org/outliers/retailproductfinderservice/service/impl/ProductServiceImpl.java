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
import org.outliers.retailproductfinderservice.objects.model.Product;
import org.outliers.retailproductfinderservice.repository.ProductRepository;
import org.outliers.retailproductfinderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product save(Product product) {
    return productRepository.saveAndFlush(product);
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> findById(String productId) {
    return productRepository.findById(productId);
  }

  @Override
  public void deleteById(String productId) {
    productRepository.deleteById(productId);
  }

  @Override
  public Page<Product> findByTenant(PageRequest pageable, String tenantId, String searchKeyWord) {
    if (StringUtils.isEmpty(searchKeyWord)) {
      return productRepository.findByTenant(pageable, tenantId);
    } else {
      return productRepository
          .findByTenantIdAndProductName(pageable, tenantId, searchKeyWord.toLowerCase());
    }
  }

  @Override
  public Product findByProductName(String productName) {
    return productRepository.findByProductName(productName);
  }
}
