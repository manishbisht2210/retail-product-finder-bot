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
import org.outliers.retailproductfinderservice.objects.model.Product;
import org.outliers.retailproductfinderservice.objects.model.Tenant;
import org.outliers.retailproductfinderservice.service.ProductService;
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
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductResource {

  private static final String URI = "products";
  private ProductService productService;
  private TenantService tenantService;

  @Autowired
  public ProductResource(ProductService productService, TenantService tenantService) {
    this.productService = productService;
    this.tenantService = tenantService;
  }

  @GetMapping("/{productId}")
  public Product findProductById(@PathVariable String productId) throws ResourceNotFoundException {
    Optional<Product> productOptional = productService.findById(productId);
    if (!productOptional.isPresent()) {
      throw new ResourceNotFoundException("productId-" + productId);
    }
    return productOptional.get();
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts(Pageable pageable,
      @RequestParam(required = false) String tenantId,
      @RequestParam(required = false) String searchKeyWord) {
    log.debug(
        "REST request to get a page of Products : {} by tenantId : {} and optional searchString : {}",
        pageable, tenantId, searchKeyWord);
    PageRequest request = PaginationUtil.generatePageRequest(pageable);
    Page<Product> page = productService.findByTenant(request, tenantId, searchKeyWord);
    HttpHeaders headers = PaginationUtil
        .generatePaginationHttpHeaders(page, String.format(URI));
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable String productId) {
    productService.deleteById(productId);
  }

  @PutMapping("/{productId}")
  public ResponseEntity<Object> updateProduct(@RequestBody Product product,
      @PathVariable String productId) {
    Optional<Product> productOptional = productService.findById(productId);
    if (!productOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    product.setProductId(productId);
    productService.save(product);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createProduct(@RequestBody Product product)
      throws ResourceNotFoundException {
    Optional<Tenant> byTenantName = tenantService
        .findByTenantName(product.getTenant().getTenantName());
    if (byTenantName.isPresent()) {
      product.setTenant(byTenantName.get());
      Product savedProduct = productService.save(product);
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
          .buildAndExpand(savedProduct.getProductId()).toUri();
      return ResponseEntity.created(location).build();
    }
    throw new ResourceNotFoundException("tenantName-" + product.getTenant().getTenantName());
  }
}
