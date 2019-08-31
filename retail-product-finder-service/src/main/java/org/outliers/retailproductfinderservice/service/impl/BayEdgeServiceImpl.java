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
import org.outliers.retailproductfinderservice.objects.model.BayEdge;
import org.outliers.retailproductfinderservice.repository.BayEdgeRepository;
import org.outliers.retailproductfinderservice.service.BayEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BayEdgeServiceImpl implements BayEdgeService {

  private BayEdgeRepository bayEdgeRepository;

  @Autowired
  public BayEdgeServiceImpl(BayEdgeRepository bayEdgeRepository) {
    this.bayEdgeRepository = bayEdgeRepository;
  }

  @Override
  public BayEdge save(BayEdge bayEdgeEdge) {
    return bayEdgeRepository.saveAndFlush(bayEdgeEdge);
  }

  @Override
  public List<BayEdge> findAll() {
    return bayEdgeRepository.findAll();
  }

  @Override
  public Optional<BayEdge> findById(String bayEdgeId) {
    return bayEdgeRepository.findById(bayEdgeId);
  }

  @Override
  public void deleteById(String bayEdgeId) {
    bayEdgeRepository.deleteById(bayEdgeId);
  }
}
